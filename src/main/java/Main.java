
package com.td.HttpServer;

public class Main {

  public static void main(String[] args) {
    ArgumentParser arguments = new ArgumentParser(args);
    HttpServer httpServer = new HttpServer(arguments.getPortNumber(), arguments.getDirectory());
    try {
      httpServer.run();
    }
    catch(UnableToOpenPortException e) {
      e.printStackTrace();
      System.out.println("\nPort number " + arguments.getPortNumber() + " can not be opened. It may be in use by another application.\n");
    }
  }
}
