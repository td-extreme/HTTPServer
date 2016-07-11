package com.td.Mocks;
import com.td.HttpServer.IFileIO;
import java.io.IOException;
import java.util.Arrays;
import java.util.ArrayList;

public class MockFileIO implements IFileIO {
  private boolean isFileReturnsTrue;
  private boolean isDirectoryReturnsTrue;
  private ArrayList<String> dirContents;

  public MockFileIO() {
    dirContents = new ArrayList<String>();
  }

  public String workingDirectory() {
    return "./";
  }

  public boolean exists(String path) {
    for (String file : dirContents) {
      if (file.equals(path)) {
        return true;
      }
    }
    return false;
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
    return dirContents.toArray(new String[dirContents.size()]);
  }

  public byte[] getContent(String fileName) throws IOException {
    if (fileName.equals("/throwIOException")) {
      throw new IOException();
    }
    return new byte[0];
  }

  public void writeContent(String filename, String body) {
    addToDirectoryContents(filename);
  }

  public void writeContent(String filename, byte[] body) {
    addToDirectoryContents(filename);
  }

  public void addToDirectoryContents(String path) {
    dirContents.add(path);
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
