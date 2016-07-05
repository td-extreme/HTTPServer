package com.td.HttpServer;

import java.util.*;

public class HttpHandlerSelector {

  HandlerRouter handlerRouter;

  public HttpHandlerSelector(HandlerRouter handlerRouter) {
    this.handlerRouter = handlerRouter;
  }

  public HttpResponse generateResponse(HttpRequest request) {
    Ihandler handler = handlerRouter.selectHandler(request);
    return handler.generateResponse(request);
  }
}
