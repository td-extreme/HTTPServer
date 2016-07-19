package com.td.HttpServer;
import java.io.IOException;

public interface IClientSocketOpenClose {
 public void closeClientConnection();
  public void openClientConnection() throws IOException;
}
