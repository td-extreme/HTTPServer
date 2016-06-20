package com.td.HttpServer;

public class ArgumentParser {

  private int portNumber;

  public ArgumentParser(String[] args) {
    portNumber = 8080;
    for (int i = 0; i < args.length; i++) {
      System.out.println(args[i]);
      if (args[i].equals("-pn")) {
        portNumber = Integer.parseInt(args[++i]);
      }
    }
  }

  public int getPortNumber() {
    return portNumber;
  }
}
