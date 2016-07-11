package com.td.HttpServer;
import java.io.IOException;

public class HttpReaderWriter {

  IMessageIO socketIO;
  IRequestBuilder builder;
  HttpResponseFormatter httpResponseFormatter;

  public HttpReaderWriter(IMessageIO messageIO, IRequestBuilder builder, HttpResponseFormatter httpResponseFormatter) {
    this.socketIO = messageIO;
    this.builder = builder;
    this.httpResponseFormatter = httpResponseFormatter;
  }

  public HttpRequest getHttpRequest() throws IOException, InvalidHttpRequestException {
    socketIO.openClientConnection();
    return builder.createRequest(socketIO);
  }

  public void sendHttpResponse(HttpResponse response)throws IOException {
    byte[] responseBytes = httpResponseFormatter.responseAsBytes(response);
    sendMessage(responseBytes);
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
