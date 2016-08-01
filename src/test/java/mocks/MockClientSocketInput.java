package com.td.Mocks;
import com.td.HttpServer.IClientSocketInput;

public class MockClientSocketInput implements IClientSocketInput {

  private String rawRequestString;
  private byte[] bytes;

  public MockClientSocketInput(String rawRequestString, byte[] bytes) {
    this.rawRequestString = rawRequestString;
    this.bytes = bytes;
  }

  public String getRawRequestString() {
    return rawRequestString;
  }

  public byte[] getBytes(int length) {
    return bytes;
  }

}
