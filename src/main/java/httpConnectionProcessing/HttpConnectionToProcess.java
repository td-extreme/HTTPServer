package com.td.HttpServer;

public class HttpConnectionToProcess {
  IClientSocketIO client;
  IHandlerRouter handlerRouter;
  IRequestBuilder httpRequestBuilder;
  IResponseBuilder httpResponseBuilder;
  IResponseWriter httpResponseWriter;

  public HttpConnectionToProcess(IClientSocketIO client, IHandlerRouter handlerRouter, IRequestBuilder httpRequestBuilder, IResponseBuilder httpResponseBuilder, IResponseWriter httpResponseWriter) {
    this.client = client;
    this.handlerRouter = handlerRouter;
    this.httpRequestBuilder = httpRequestBuilder;
    this.httpResponseBuilder = httpResponseBuilder;
    this.httpResponseWriter = httpResponseWriter;
  }

  public void execute() {
    try {
      IHandler handler = getHandler();
      HttpResponse response = httpResponseBuilder.generateResponse(handler);
      sendResponse(response);
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

  private void sendResponse(HttpResponse response) throws BadConnectionException {
    httpResponseWriter.sendHttpResponse(client.clientSocketOutput(), response);
  }
}
