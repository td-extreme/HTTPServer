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
    socketIO.openClientConnection();
    while(true) {
      String message = getMessage();
      try {
        return builder.createRequest(message);
      }
      catch (InvalidHttpRequestException e) {
        //TODO change this so that it creates a HttpResposne and sends that to output.
        sendMessage(badRequest);
        socketIO.closeClientConnection();
      }
    }
  }

  public void sendHttpResponse(HttpResponse response)throws IOException {
    sendMessage(response.responseLine());
    sendMessage("\n");
    sendMessage(response.headers());
    sendMessage("\r\n\r\n");
    sendMessage(response.body());
    socketIO.closeClientConnection();
  }

  private String getMessage() throws IOException {
    return socketIO.getMessage();
  }

  private void sendMessage(byte[] message) throws IOException {
    socketIO.sendBytes(message);
  }
  private void sendMessage(String message) throws IOException {
    socketIO.sendMessage(message);
  }
}
