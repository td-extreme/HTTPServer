package com.td.HttpServer;

import java.util.HashMap;

public class HttpResponseBuilder implements IResponseBuilder {

  public HttpResponse generateResponse(IHandler handler) {
    Object[] responseArray = handler.call();

    return new HttpResponse((int)responseArray[0], (HashMap<String, String>)responseArray[1], (byte[])responseArray[2]);
  }
}
