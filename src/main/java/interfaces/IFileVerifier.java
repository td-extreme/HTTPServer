package com.td.HttpServer;
import java.io.IOException;

public interface IFileVerifier {
  public boolean exists(String path) throws IOException;
  public boolean isFile(String path);
  public boolean isDirectory(String path);
}
