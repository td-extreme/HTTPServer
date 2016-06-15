package com.td.HttpServer;
import java.io.IOException;

public class HttpServerRunner {

  HttpReaderWriter httpReaderWriter;
  HttpHandlerSelector httpHandlerSelector;

  public HttpServerRunner(HttpReaderWriter readerWriter, HttpHandlerSelector handlerSelector) {
    httpReaderWriter = readerWriter;
    httpHandlerSelector = handlerSelector;
  }

  public void runServer() throws IOException {
    HttpRequest request;
    HttpResponse response;
    while (true) {
      request = httpReaderWriter.getHttpRequest();
      response = httpHandlerSelector.generateResponse(request);
      httpReaderWriter.sendHttpResponse(response);
    }
  }
}
