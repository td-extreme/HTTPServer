package com.td.HttpServer;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.BufferedReader;

public class FileIO implements IFileIO {

  private String workingDirectory;
  private File dir;

  public FileIO() {
    workingDirectory = "./";
  }

  public FileIO(String directory) {
    workingDirectory = directory;
  }

  public String workingDirectory() {
    return workingDirectory;
  }

  public String getFileContents(String fileName) {
    try {
      StringBuilder fileToOpen = new StringBuilder();
      fileToOpen.append(workingDirectory);
      fileToOpen.append(fileName);
      File file = new File(fileToOpen.toString());
      BufferedReader reader = new BufferedReader(new FileReader(file));
      StringBuilder rtnString = new StringBuilder();
      String inputLine = null;
      while((inputLine = reader.readLine()) != null) {
        rtnString.append(inputLine);
        rtnString.append("\n");
      }
      return rtnString.toString();

    }
    catch (IOException e) {
      return "Error: File Not Found";
    }

  }
}


