package com.td.HttpServer;

import java.util.HashMap;

public class HandlerBadRequest implements IHandler {

  public Object[] call() {
    HashMap<String, String> headers = new HashMap<String, String>();
    headers.put("Content-Type", ContentType.text);
    Object[] returnArray = new Object[3];
    returnArray[0] = 400;
    returnArray[1] = headers;
    returnArray[2] = "Bad Request".getBytes();
    return returnArray;
  }
}
