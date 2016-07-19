package com.td.Mocks;
import com.td.HttpServer.IMessageIO;

public class MockSocketIO implements IMessageIO {
  private byte[] receivedBytes;

  public MockSocketIO() {
   receivedBytes = new byte[0];
  }
  public String getMessage() { return ""; }
  public byte[] getBytes(int length) { return new byte[0]; }
  public void sendBytes(byte[] bytes) {
    receivedBytes = bytes;
  }
  public void sendMessage(String message) {}
  public void openClientConnection() {}
  public void closeClientConnection() {}

  public byte[] getReceivedBytes() {
    return receivedBytes;
  }
}
