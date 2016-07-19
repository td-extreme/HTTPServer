package com.td.HttpServer;
import java.io.IOException;

public class HttpServerRunner {

  HandlerRouter handlerRouter;
  IRequestBuilder httpRequestBuilder;
  HttpResponseWriter httpResponseWriter;
    public HttpServerRunner(HandlerRouter handlerRouter, IRequestBuilder httpRequestBuilder, HttpResponseWriter httpResponseWriter) {
    this.handlerRouter = handlerRouter; 
    this.httpRequestBuilder = httpRequestBuilder;
    this.httpResponseWriter = httpResponseWriter;
  }

  public void run(ClientSocketIO client) {
   HttpRequest request;
    Ihandler handler;
    HttpResponse response;
    try {
      request = httpRequestBuilder.getNextRequest(client);
      handler = handlerRouter.selectHandler(request);
    }
    catch (InvalidHttpRequestException e) {
      handler = handlerRouter.selectHandlerBadRequest();
    }
    catch (IOException e) {
      return;
    }
    response = handler.generateResponse();
    try {
      httpResponseWriter.sendHttpResponse(client, response);
    }
    catch (IOException e) { }
  }
}
