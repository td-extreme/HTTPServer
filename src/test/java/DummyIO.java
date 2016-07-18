package com.td.Mocks;
import com.td.HttpServer.IMessageIO;

public class DummyIO implements IMessageIO {
  public String getMessage() { return ""; }
  public byte[] getBytes(int length) { return new byte[0]; }
  public void sendBytes(byte[] bytes) {}
  public void sendMessage(String message) {}
  public void openClientConnection() {}
  public void closeClientConnection() {}
}
