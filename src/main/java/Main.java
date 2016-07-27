
package com.td.HttpServer;

public class Main {

  public static void main(String[] args) {
    ArgumentParser arguments = new ArgumentParser(args);
    HttpServer httpServer = new HttpServer(arguments.getPortNumber(), arguments.getDirectory());
    httpServer.run();
  }
}
