package com.td.HttpServer;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.BufferedReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.Path;
import java.util.*;

public class FileIO implements IFileIO {

  private String workingDirectory;

  public FileIO(String directory) {
    workingDirectory = directory;
  }

  public String workingDirectory() {
    return workingDirectory;
  }

  public boolean isPathFile(String path) throws IOException {
    File checkFile = new File(getPath(path));
    return checkFile.isFile();
  }

  public byte[] getContent(String fileName) throws IOException {
    Path path = Paths.get(getPath(fileName));
    return Files.readAllBytes(path);
  }

  public ArrayList<String> getFiles(String directory) throws IOException {
    File dir = new File(getPath(directory));
    if (!dir.isDirectory()) {
      throw new IOException();
    }
    ArrayList<String> list =  new ArrayList<String>(Arrays.asList(dir.list()));
    return list;
  }

  private String getPath(String fileName) {
    StringBuilder fileToOpen = new StringBuilder();
    fileToOpen.append(workingDirectory);
    fileToOpen.append(fileName);
    return fileToOpen.toString();
  }
}
