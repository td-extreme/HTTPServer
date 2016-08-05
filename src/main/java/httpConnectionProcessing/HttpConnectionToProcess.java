package com.td.HttpServer;

public class HttpConnectionToProcess {
  IClientSocketIO client;
  IHandlerRouter handlerRouter;
  IRequestBuilder httpRequestBuilder;
  IResponseWriter httpResponseWriter;

  public HttpConnectionToProcess(IClientSocketIO client, IHandlerRouter handlerRouter, IRequestBuilder httpRequestBuilder, IResponseWriter httpResponseWriter) {
    this.client = client;
    this.handlerRouter = handlerRouter;
    this.httpRequestBuilder = httpRequestBuilder;
    this.httpResponseWriter = httpResponseWriter;
  }

  public void execute() {
    HttpRequest request;
    try {
      request = getRequest();
      IHandler handler = getHandler(request);
      sendResponse(handler, request);
    }
    catch (BadConnectionException e) {
      e.printStackTrace();
    }
    finally {
      client.closeClientConnection();
    }
  }

  private HttpRequest getRequest() throws BadConnectionException {
    try {
      return httpRequestBuilder.getNextRequest(client.clientSocketInput());
    }
    catch (InvalidHttpRequestException e) {
      e.printStackTrace();
      return null;
    }
  }

  private IHandler getHandler(HttpRequest request) throws BadConnectionException {
    IHandler handler;
    if (request == null) {
      handler = handlerRouter.selectHandlerBadRequest();
    } else {
      handler = handlerRouter.selectHandler(request);
    }
    return handler;
  }

  private void sendResponse(IHandler handler, HttpRequest request) throws BadConnectionException {
    HttpResponse response = handler.generateResponse(request);
    httpResponseWriter.sendHttpResponse(client.clientSocketOutput(), response);
  }
}
