package com.td.HttpServer;
import java.io.IOException;

public class HttpResponseWriter {

  HttpResponseFormatter httpResponseFormatter;

  public HttpResponseWriter(HttpResponseFormatter httpResponseFormatter) {
    this.httpResponseFormatter = httpResponseFormatter;
  }

  public void sendHttpResponse(IMessageIO client, HttpResponse response)throws IOException {
    byte[] responseBytes = httpResponseFormatter.responseAsBytes(response);
    sendMessage(client, responseBytes);
  }

  private void sendMessage(IMessageIO client, byte[] message) throws IOException {
    client.sendBytes(message);
  }
}
