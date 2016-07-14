package com.td.HttpServer;
import java.io.IOException;

public interface IFileWriter {
  public void writeContent(String fileName, byte[] body) throws IOException;
  public void writeContent(String fileName, String body) throws IOException;
}
