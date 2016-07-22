package com.td.HttpServer;

public class ConnectionProcessRunnerSingleThread implements IConnectionProcessRunner {
  public void execute(HttpConnectionToProcess httpConnectionToProcess) {
    httpConnectionToProcess.execute();
  }
}
