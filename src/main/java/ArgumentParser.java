package com.td.HttpServer;

public class ArgumentParser {

  private int portNumber;
  private String dir;

  public ArgumentParser(String[] args) {
    portNumber = 8080;
    dir = "./";
    for (int i = 0; i < args.length; i++) {
      try {
        if (args[i].equals("--port") || args[i].equals("-p")) {
          portNumber = Integer.parseInt(args[++i]);
        }
        if (args[i].equals("--dir") || args[i].equals("-d")) {
          dir = args[++i];
        }
        if (args[i].equals("--help") || args[i].equals("-h")) {
          helpMessage();
          System.exit(0);
        }
      }
      catch (Exception e) {
        helpMessage();
        System.exit(1);
      }
    }
  }

  public String getDirectory() {
    return dir;
  }

  public int getPortNumber() {
    return portNumber;
  }

  public void helpMessage() {
    String format = "%-15s:%-30s\n";
    System.out.print("HttpServer\n [options]\n");
    System.out.printf(format, "--port ####", "Use port number specified, if flag is not use default is 8080.");
    System.out.printf(format, "-p ####", "Use port number specified, if flag is not use default is 8080.");
    System.out.printf(format, "--dir  ####", "Use path specified for root directory of server content.");
    System.out.printf(format, "-d ####", "Use port number specified, if flag is not use default is 8080.");
    System.out.printf(format, "--help", "Display this message");
    System.out.printf(format, "-h", "Display this message");
  }
}
