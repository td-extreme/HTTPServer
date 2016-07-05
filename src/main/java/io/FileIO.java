package com.td.HttpServer;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.BufferedReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.Path;

public class FileIO implements IFileIO {

  private String workingDirectory;

  public FileIO(String directory) {
    workingDirectory = directory;
  }

  public boolean exists(String path) {
    File checkFile = new File(getPath(path));
    return checkFile.exists();
  }

  public boolean isFile(String path) {
      File checkFile = new File(getPath(path));
      return checkFile.isFile();
  }

  public String workingDirectory() {
    return workingDirectory;
  }

  public byte[] getContent(String fileName) throws IOException {
      Path path = Paths.get(getPath(fileName));
      return Files.readAllBytes(path);
  }

  private String getPath(String fileName) {
    return workingDirectory + fileName;
  }
}
