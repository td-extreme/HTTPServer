package com.td.HttpServer;

import java.util.*;

public class HandlerRouter implements IHandlerRouter {

  private DefaultHandlerRouter defaultHandlerRouter;

  public HandlerRouter(DefaultHandlerRouter defaultHandlerRouter) {
    this.defaultHandlerRouter = defaultHandlerRouter;
  }

  public IHandler selectHandlerBadRequest() {
    return new HandlerBadRequest();
  }

  public IHandler selectHandler(HttpRequest request) {
    return defaultHandlerRouter.selectHandler(request);
  }
}
