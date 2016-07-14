package com.td.HttpServer;
import java.io.IOException;

public interface IFileIO extends IFileVerifier, IFileReader, IFileWriter {
  public String workingDirectory();
}
