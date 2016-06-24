package com.td.HttpServer;

public class ParseString implements IParser {

  public String[] parse(String request) {
    return request.split("\\s+");
  }
}
