package com.td.HttpServer;
import java.io.IOException;

public interface IFileIO {
  public String workingDirectory();
  public byte[] getContent(String fileName) throws IOException;
}
