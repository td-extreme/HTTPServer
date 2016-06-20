package com.td.HttpServer;
import java.io.IOException;

public class HttpReaderWriter {

  IMessageIO socketIO;
  IValidator httpValidator;
  IRequestBuilder builder;
  private final String badRequest = "HTTP/1.1 400 Bad Request";

  public HttpReaderWriter(IMessageIO messageIO, IRequestBuilder builder, IValidator httpValidator) {
    this.socketIO = messageIO;
    this.httpValidator = httpValidator;
    this.builder = builder;
  }

  public HttpRequest getHttpRequest() throws IOException {
    while(true) {
      String message = getMessage();
      if (httpValidator.isValid(message)) {
        return builder.createRequest(message);
      } else {
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
