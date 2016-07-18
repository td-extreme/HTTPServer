package com.td.HttpServer;
import java.io.IOException;

public class HttpRequestReader implements IRequestReader {

  public String getMessage(IMessageIO client) throws IOException {
    return client.getMessage();
  }

  public byte[] getBytes(IMessageIO client, int length) throws IOException {
    return client.getBytes(length);
  }
}
