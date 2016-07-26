package com.td.HttpServer;
import java.util.Collection;
import java.util.ArrayList;
public class CustomHandlerRouter implements IHandlerRouter {

  private Collection<CustomRoute> routes;
  private DefaultHandlerRouter defaultHandlerRouter;

  public CustomHandlerRouter(DefaultHandlerRouter defaultHandlerRouter) {
    this.defaultHandlerRouter = defaultHandlerRouter;
    this.routes = new ArrayList<CustomRoute>();
  }

  public void addRoute(String method, String path, IHandler handler) {
    CustomRoute newRoute = new CustomRoute(method, path, handler);
    routes.add(newRoute);
  }

  public IHandler selectHandlerBadRequest() {
    return new HandlerBadRequest();
  }

  public IHandler selectHandler(HttpRequest request) {
    for (CustomRoute route : routes) {
      if (route.match(request.method(), request.path())) {
        return route.getHandler();
      }
    }
    return defaultHandlerRouter.selectHandler(request);
  }
}
