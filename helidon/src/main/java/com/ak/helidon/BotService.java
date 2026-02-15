package com.ak.helidon;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.model.User;
import com.pengrad.telegrambot.request.SendMessage;
import io.helidon.config.Config;
import io.helidon.webserver.http.HttpRules;
import io.helidon.webserver.http.HttpService;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

final class BotService implements HttpService {
  private static final Config CONFIG = Config.create().get("bot");
  private static final String BOT_TOKEN = CONFIG.get("token").asString().orElseThrow();
  private static final long BOT_ADMIN = CONFIG.get("admin").asLong().orElseThrow();

  private final Map<Long, Integer> answers = new ConcurrentHashMap<>();
  private final Map<Long, String> users = new ConcurrentHashMap<>();
  private final Set<Long> winners = new HashSet<>();
  private final AtomicLong nowWinner = new AtomicLong(0);

  public BotService() {
    TelegramBot bot = new TelegramBot(BOT_TOKEN);
    bot.setUpdatesListener(updates -> {
      updates.stream().map(Update::message).filter(Objects::nonNull).forEach(message -> {
        String text = Objects.requireNonNullElse(message.text(), "").strip();
        if (!text.isBlank()) {
          long chatId = message.chat().id();
          User user = message.from();
          String send = "";
          if (user.id().equals(BOT_ADMIN)) {
            if ("/start".equals(text)) {
              answers.clear();
              nowWinner.set(0L);
              send = "раунд %d".formatted(winners.size() + 1);
            }
            else if ("/stop".equals(text)) {
              Optional<Integer> win = answers.entrySet().stream()
                  .collect(Collectors.groupingBy(Map.Entry::getValue, Collectors.counting()))
                  .entrySet().stream().sorted(Map.Entry.comparingByKey()).filter(e -> e.getValue() == 1).findAny().map(Map.Entry::getKey);
              if (win.isPresent()) {
                Long winId = answers.entrySet().stream().filter(e -> e.getValue().equals(win.get())).findFirst().orElseThrow().getKey();
                send = "%s победил".formatted(users.get(winId));
                winners.add(winId);
                nowWinner.set(winId);
              }
              else {
                send = "нет победителей";
              }
            }
          }
          else if ("/start".equals(text)) {
            send = "%d игроков".formatted(answers.size());
          }
          else if (winners.contains(user.id())) {
            send = "Вы %s уже победили".formatted(users.get(user.id()));
          }

          if (send.isEmpty()) {
            try {
              users.putIfAbsent(user.id(),
                  String.join(" ",
                      "@%s".formatted(Objects.requireNonNullElse(user.username(), "")),
                      Objects.requireNonNullElse(user.firstName(), ""),
                      Objects.requireNonNullElse(user.lastName(), ""))
              );
              int number = Math.clamp(Integer.parseInt(text), 1, 100);
              Integer absent = answers.putIfAbsent(user.id(), number);
              send = "%d, сейчас %d игроков".formatted(Objects.requireNonNullElse(absent, number), answers.size());
            }
            catch (NumberFormatException _) {
              send = "[1..100]";
            }
          }
          bot.execute(new SendMessage(chatId, send));
        }
      });
      return UpdatesListener.CONFIRMED_UPDATES_ALL; // Подтверждаем получение
    });
  }

  @Override
  public void routing(HttpRules rules) {
    rules.get("/", (_, res) -> {
      String send;
      if (nowWinner.get() == 0L) {
        send = "%d%n%n%s".formatted(answers.size(), users.values().stream().collect(Collectors.joining("%n".formatted())));
      }
      else {
        send = this.answers.entrySet().stream()
            .collect(Collectors.toMap(e -> users.get(e.getKey()), e -> {
              if (nowWinner.get() == e.getKey()) {
                return e.getValue() + " WIN ";
              }
              else {
                return e.getValue();
              }
            }))
            .entrySet().stream().map(Object::toString).collect(Collectors.joining("%n".formatted()));
      }
      res.send(send);
    });
  }
}
