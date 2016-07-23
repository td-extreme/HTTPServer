package com.td.HttpServer;
import java.io.IOException;

public interface IClientSocketOutput {
  public void sendString(String rawString) throws IOException;
  public void sendBytes(byte[] bytes) throws IOException;
}
