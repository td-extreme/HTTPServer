package com.td.Mocks;
import java.io.IOException;
import com.td.HttpServer.IRequestReader;
import com.td.HttpServer.IMessageIO;

public class MockHttpRequestReader implements IRequestReader {
  String returnMessage;
  byte[] returnBytes;
  boolean getBytesWasCalled;

  public MockHttpRequestReader(String returnMessage, byte[] returnBytes) {
    getBytesWasCalled = false;
    this.returnMessage = returnMessage;
    this.returnBytes = returnBytes;
  }

  public String getMessage(IMessageIO client) throws IOException {
    return returnMessage;
  }

  public byte[] getBytes(IMessageIO client, int length) throws IOException {
    getBytesWasCalled = true;
    return returnBytes;
  }

  public boolean wasGetBytesCalled() {
    return getBytesWasCalled;
  }
}
