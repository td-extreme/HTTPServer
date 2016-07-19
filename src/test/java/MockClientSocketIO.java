package com.td.Mocks;
import com.td.HttpServer.IClientSocketIO;

public class MockClientSocketIO implements IClientSocketIO {
  private byte[] receivedBytes;
  private String rawRequestString;
  private byte[] bytes;

  public MockClientSocketIO() {
    this.rawRequestString = "";
    this.receivedBytes = new byte[0];
  }

  public MockClientSocketIO(String rawRequestString, byte[] bytes) {
    this.rawRequestString = rawRequestString;
    this.bytes = bytes;
    this.receivedBytes = new byte[0];
  }

  public String getRawRequestString() {
    return rawRequestString;
  }

  public byte[] getBytes(int length) {
    return bytes;
  }

  public void sendBytes(byte[] bytes) {
    receivedBytes = bytes;
  }

  public void sendString(String rawString) {}
  public void openClientConnection() {}
  public void closeClientConnection() {}

  public byte[] getReceivedBytes() {
    return receivedBytes;
  }
}
