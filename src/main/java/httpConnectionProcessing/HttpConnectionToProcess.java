package com.td.HttpServer;

public class HttpConnectionToProcess {
  IClientSocketIO client;
  Router handlerRouter;
  IRequestBuilder httpRequestBuilder;
  IResponseWriter httpResponseWriter;

  public HttpConnectionToProcess(IClientSocketIO client, Router handlerRouter, IRequestBuilder httpRequestBuilder, IResponseWriter httpResponseWriter) {
    this.client = client;
    this.handlerRouter = handlerRouter;
    this.httpRequestBuilder = httpRequestBuilder;
    this.httpResponseWriter = httpResponseWriter;
  }

  public void execute() {
    try {
      IHandler handler = getHandler();
      sendResponse(handler);
    }
    catch (BadConnectionException e) {
      e.printStackTrace();
    }
    finally {
      client.closeClientConnection();
    }
  }

  private IHandler getHandler() throws BadConnectionException {
    HttpRequest request;
    IHandler handler;
    try {
      request = httpRequestBuilder.getNextRequest(client.clientSocketInput());
      handler = handlerRouter.selectHandler(request);
    }
    catch (InvalidHttpRequestException e) {
      e.printStackTrace();
      handler = handlerRouter.selectHandlerBadRequest();
    }
    return handler;
  }

  private void sendResponse(IHandler handler) throws BadConnectionException {
    HttpResponse response = handler.generateResponse();
    httpResponseWriter.sendHttpResponse(client.clientSocketOutput(), response);
  }
}
