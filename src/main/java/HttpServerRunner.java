package com.td.HttpServer;
import java.io.IOException;

public class HttpServerRunner {

  HttpReaderWriter httpReaderWriter;
  HandlerRouter handlerRouter;

  public HttpServerRunner(HttpReaderWriter httpReaderWriter, HandlerRouter handlerRouter) {
    this.httpReaderWriter = httpReaderWriter;
    this.handlerRouter = handlerRouter; 
  }

  public void runServer() throws IOException {
    HttpRequest request;
    Ihandler handler;
    HttpResponse response;

    while (true) {
      try {
        request = httpReaderWriter.getHttpRequest();
        handler = handlerRouter.selectHandler(request);
      }
      catch (InvalidHttpRequestException e) {
        handler = handlerRouter.selectHandlerBadRequest();
      }
      response = handler.generateResponse();
      httpReaderWriter.sendHttpResponse(response);
   }
  }
}
