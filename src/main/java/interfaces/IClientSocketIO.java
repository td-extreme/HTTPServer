package com.td.HttpServer;
import java.io.IOException;

public interface IClientSocketIO {
  public String getRawRequestString() throws IOException;
  public void sendString(String rawString) throws IOException;
  public void sendBytes(byte[] bytes) throws IOException;
  public byte[] getBytes(int length) throws IOException;
  public void closeClientConnection();
  public void openClientConnection() throws IOException;
}
