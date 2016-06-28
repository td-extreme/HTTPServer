package com.td.HttpServer;
import java.io.IOException;
import java.util.*;

public interface IFileIO {
  public String workingDirectory();
  public byte[] getContent(String fileName) throws IOException;
  public ArrayList<String> getFiles (String directory) throws IOException;
  public boolean isPathFile(String path) throws IOException;
}
