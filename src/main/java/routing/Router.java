package com.td.HttpServer;

public class Router implements IHandlerRouter {

  private IHandlerRouter defaultHandlerRouter;
  private IHandlerRouter customHandlerRouter;

  public Router(DefaultHandlerRouter defaultHandlerRouter) {
    this.defaultHandlerRouter = defaultHandlerRouter;
  }

  public IHandler selectHandlerBadRequest() {
    return new HandlerBadRequest();
  }

  public IHandler selectHandler(HttpRequest request) {
    IHandler handler = null;
    if (customHandlerRouter != null) {
      handler = customHandlerRouter.selectHandler(request);
    }
    if (handler == null) {
      handler = defaultHandlerRouter.selectHandler(request);
    }
    return handler;
  }

  public void setCustomHandlerRouter(IHandlerRouter customHandlerRouter) {
    this.customHandlerRouter = customHandlerRouter;
  }
}
