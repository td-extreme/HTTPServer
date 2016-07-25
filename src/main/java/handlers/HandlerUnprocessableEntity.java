package com.td.HttpServer;

import java.util.HashMap;

public class HandlerUnprocessableEntity implements IHandler {

  private String message;

  public HandlerUnprocessableEntity(String message) {
    this.message = message;
  }

  public Object[] call() {
    Object[] returnArray = new Object[3];
    HashMap<String, String> headers = new HashMap<String, String>();
    headers.put("Content-Type", ContentType.text);
    returnArray[0] = 422;
    returnArray[1] = headers;
    returnArray[2] = message.getBytes();
    return returnArray;
  }
}
