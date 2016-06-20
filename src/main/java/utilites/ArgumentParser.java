package com.td.HttpServer;

public class ArgumentParser {

  private int portNumber;
  public ArgumentParser(String[] args) {
    portNumber = 8080;
    for (int i = 0; i < args.length; i++) {
      try {
        if (args[i].equals("--port") || args[i].equals("-p")) {
          portNumber = Integer.parseInt(args[++i]);
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

  public int getPortNumber() {
    return portNumber;
  }

  public void helpMessage() {
    // TODO clean up this up
    // possible create a map <flag, description>
    // and then loop through map printing out each.
    String format = "%-15s:%-30s\n";
    System.out.print("HttpServer\n [options]\n");
    System.out.printf(format, "--port ####", "Use port number specified, if flag is not use default is 8080.");
    System.out.printf(format, "-p ####", "Use port number specified, if flag is not use default is 8080.");
    System.out.printf(format, "--help", "Display this message");
    System.out.printf(format, "-h", "Display this message");
  }
}
