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

  public String workingDirectory() {
    return workingDirectory;
  }

  public byte[] getContent(String fileName) throws IOException {
      Path path = Paths.get(getPath(fileName));
      return Files.readAllBytes(path);
  }

  private String getPath(String fileName) {
    StringBuilder fileToOpen = new StringBuilder();
    fileToOpen.append(workingDirectory);
    fileToOpen.append(fileName);
    return fileToOpen.toString();
  }
}
