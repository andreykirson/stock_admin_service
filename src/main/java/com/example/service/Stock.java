package com.example.service;

public class Stock {

  public String symbol;
  public String name;
  public String date;
  public String open;
  public String high;
  public String low;
  public String close;
  public String volume;

  public Stock( String symbol, String name, String date, String open,
      String high, String low, String close, String volume) {
    this.symbol = symbol;
    this.name = name;
    this.date = date;
    this.open = open;
    this.high = high;
    this.low = low;
    this.close = close;
    this.volume = volume;
  }
}
