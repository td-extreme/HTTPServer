package com.td.HttpServer;
import java.io.IOException;

public class HttpServerRunner {
  private HandlerRouter handlerRouter;
  private IRequestBuilder httpRequestBuilder;
  private HttpResponseWriter httpResponseWriter;

  public HttpServerRunner(HandlerRouter handlerRouter, IRequestBuilder httpRequestBuilder, HttpResponseWriter httpResponseWriter) {
    this.handlerRouter = handlerRouter; 
    this.httpRequestBuilder = httpRequestBuilder;
    this.httpResponseWriter = httpResponseWriter;
  }

  public void run(ClientSocketIO client) {
    try {
      Ihandler handler = getHandler(client);
      sendResponse(client, handler);
    }
    catch (BadConnectionException e) {
      e.printStackTrace();
      client.closeClientConnection();
    }
  }

  private Ihandler getHandler(ClientSocketIO client) throws BadConnectionException {
    HttpRequest request;
    Ihandler handler;
    try {
      request = httpRequestBuilder.getNextRequest(client);
      handler = handlerRouter.selectHandler(request);
    }
    catch (InvalidHttpRequestException e) {
      e.printStackTrace();
      handler = handlerRouter.selectHandlerBadRequest();
    }
    return handler;
  }

  private void sendResponse(ClientSocketIO client, Ihandler handler) throws BadConnectionException {
    HttpResponse response = handler.generateResponse();
    httpResponseWriter.sendHttpResponse(client, response);
  }
}
