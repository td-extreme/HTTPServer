package com.td.Mocks;
import com.td.HttpServer.IClientSocketOutput;

public class MockClientSocketOutput implements IClientSocketOutput {
  private byte[] receivedBytes;

  public void sendBytes(byte[] bytes) {
    receivedBytes = bytes;
  }

  public void sendString(String rawString) {}
  public byte[] getReceivedBytes() {
    return receivedBytes;
  }
}
