package com.td.HttpServer;
import java.io.IOException;

public interface IFileReader {
  public byte[] getContent(String fileName) throws IOException;
  public String[] getFiles(String directory) throws IOException;
}
