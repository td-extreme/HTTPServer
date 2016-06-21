package com.td.HttpServer;
import java.io.IOException;

public class HttpReaderWriter {

  IMessageIO socketIO;
  IRequestBuilder builder;
  // TODO after creating a HttpResponseBuilder class change badRequest from a string to a HttpResponse object
  private final String badRequest = "HTTP/1.1 400 Bad Request";

  public HttpReaderWriter(IMessageIO messageIO, IRequestBuilder builder ) {
    this.socketIO = messageIO;
    this.builder = builder;
  }

  public HttpRequest getHttpRequest() throws IOException {
    while(true) {
      String message = getMessage();
      try {
        return builder.createRequest(message);
      }
      catch (InvalidHttpRequestException e) {
        sendMessage(badRequest);
      }
    }
  }

  public void sendHttpResponse(HttpResponse response)throws IOException {
    sendMessage(response.toString());
  }

  private String getMessage() throws IOException {
    return socketIO.getMessage();
  }

  private void sendMessage(String message) throws IOException {
    socketIO.sendMessage(message);
  }
}
