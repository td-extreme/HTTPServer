package com.td.Mocks;
import com.td.HttpServer.IFileIO;
import java.io.IOException;

public class MockFileIO implements IFileIO {
  private boolean isFileReturnsTrue;
  private boolean isDirectoryReturnsTrue;
  private String[] dirContents;

  public String workingDirectory() {
    return "./";
  }

  public boolean exists(String path) {
    return true;
  }

  public boolean isFile(String path) {
    return isFileReturnsTrue;
  }

  public boolean isDirectory(String path) {
    return isDirectoryReturnsTrue;
  }

  public String[] getFiles(String directory) throws IOException {
    if (directory.equals("/throwIOException")) {
      throw new IOException();
    }
    return dirContents;
  }

  public byte[] getContent(String fileName) throws IOException {
    if (fileName.equals("/throwIOException")) {
      throw new IOException();
    }
    return new byte[0];
  }

  public void writeContent(String filename, String body) { }
  public void writeContent(String filename, byte[] body) { }

  public void setDirectoryContents(String[] contents) {
    this.dirContents = contents;
  }

  public void setIsFileTrue() {
    isFileReturnsTrue = true;
  }

  public void setIsFileFalse() {
    isFileReturnsTrue = false;
  }

  public void setIsDirectoryTrue() {
    isDirectoryReturnsTrue = true;
  }

  public void setIsDirectoryFalse() {
    isDirectoryReturnsTrue = false;
  }
}
