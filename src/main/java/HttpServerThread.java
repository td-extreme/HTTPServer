package com.td.HttpServer;

public class HttpServerThread implements Runnable {
  ClientSocketIO client;
  HandlerRouter handlerRouter;
  IRequestBuilder httpRequestBuilder;
  HttpResponseWriter httpResponseWriter;

  
  public HttpServerThread(ClientSocketIO client, HandlerRouter handlerRouter, IRequestBuilder httpRequestBuilder, HttpResponseWriter httpResponseWriter) {
    this.client = client;
    this.handlerRouter = handlerRouter;
    this.httpRequestBuilder = httpRequestBuilder;
    this.httpResponseWriter = httpResponseWriter;
  }

  public void run() {
    try {
      Ihandler handler = getHandler();
      sendResponse(handler);
    }
    catch (BadConnectionException e) {
      e.printStackTrace();
    }
    finally {
      client.closeClientConnection();
    }
  }

  private Ihandler getHandler() throws BadConnectionException {
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

  private void sendResponse(Ihandler handler) throws BadConnectionException {
    HttpResponse response = handler.generateResponse();
    httpResponseWriter.sendHttpResponse(client, response);
  }
}
