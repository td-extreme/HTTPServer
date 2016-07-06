package com.td.HttpServerMocks;
import com.td.HttpServer.IFileIO;
import java.io.IOException;

public class MockFileIO implements IFileIO {
  public String workingDirectory() { return "./"; }
  public boolean exists(String path) { return true; }
  public boolean isFile(String path) { return true; }
  public boolean isDirectory(String path) { return false; }
  public String[] getFiles(String directory) throws IOException {
    return new String[0];
  }
  public byte[] getContent(String fileName) throws IOException {
    if (fileName.equals("/throwIOException")) { throw new IOException(); }
    return new byte[0];
  }
}
