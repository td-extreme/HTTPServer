package com.td.HttpServer;
import java.io.IOException;

public interface IMessageIO {
  public String getMessage() throws IOException;
  public void sendMessage(String message) throws IOException;
}
