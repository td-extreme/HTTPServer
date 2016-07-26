package com.td.HttpServer;

import java.util.HashMap;

public class HandlerFileNotFound implements IHandler {

  public Object[] call() {
    HashMap<String, String> headers = new HashMap<String, String>();
    headers.put("Content-Type", ContentType.text);
    Object[] returnArray = new Object[3];
    returnArray[0] = 404;
    returnArray[1] = headers;
    returnArray[2] = "File not Found".getBytes();
    return returnArray;
  }
}
