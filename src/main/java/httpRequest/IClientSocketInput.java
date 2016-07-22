package com.td.HttpServer;
import java.io.IOException;

public interface IClientSocketInput {
  public String getRawRequestString() throws IOException;
  public byte[] getBytes(int length) throws IOException;
}
