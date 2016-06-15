package com.td.HttpServer;

public interface IFileIO {
  public String workingDirectory();
  public String getFileContents(String fileName);
}

