package com.td.HttpServer;
import java.io.IOException;

public class HttpReaderWriter {

  IMessageIO socketIO;
  IValidator httpValidator;
  private final String badRequest = "HTTP/1.1 400 Bad Request";

  public HttpReaderWriter(IMessageIO messageIO, IValidator validator) {
    socketIO = messageIO;
    httpValidator = validator;
  }

  public HttpRequest getHttpRequest() throws IOException {
    while(true) {
      String messageArray[] = getMessage();
      if (httpValidator.isValid(messageArray)) {
        return new HttpRequest(messageArray);
      } else {
        sendMessage(badRequest);
      }
    }
  }

  public void sendHttpResponse(HttpResponse response)throws IOException {
    sendMessage(response.toString());
  }

  private String[] getMessage() throws IOException {
    return socketIO.getMessage().split("\\s+");
  }

  private void sendMessage(String message) throws IOException {
    socketIO.sendMessage(message);
  }
}
