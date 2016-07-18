package com.td.HttpServer;
import java.io.IOException;

public interface IRequestReader {
  public String getMessage(IMessageIO client) throws IOException;
  public byte[] getBytes(IMessageIO client, int length) throws IOException;
}

