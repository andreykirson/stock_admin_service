package com.example.service;

import com.example.api.StockService;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Random;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Flux;

@Controller
public class StockServiceImpl implements StockService {

  @Override
  public Flux<Stock> getStocks() {
    return Flux.range(0, Integer.MAX_VALUE).flatMap(
            i -> getStock("Tesla Inc", "Tesla Motors, Inc.")
        )
        .log()
        .repeat(Long.MAX_VALUE)
        .delayElements(Duration.ofSeconds(1));
  }

  private static Flux<Stock> getStock(String symbol, String name) {
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSSZ");
    DecimalFormat decimalFormat = new DecimalFormat("0.00");

    double openDouble = new Random().nextDouble(0.01, 100_000);

    String close = decimalFormat.format(new Random().nextDouble(0.01, 100_000));
    String high = decimalFormat.format(
        Math.max(new Random().nextDouble(0.01, 100_000), openDouble));
    String low = decimalFormat.format(Math.min(new Random().nextDouble(0.01, 100_000), openDouble));
    String open = decimalFormat.format(openDouble);
    String volume = String.valueOf(new Random().nextInt(1, 100_000_000));
    String date = dateFormat.format(new Date());

    return Flux.just(new Stock(symbol, name, date, open, high, low, close, volume));
  }
}
