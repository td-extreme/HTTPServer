package com.td.HttpServer;

public class ConnectionProcessRunnerMultiThread implements IConnectionProcessRunner {
  public void execute(HttpConnectionToProcess httpConnectionToProcess) {
    HttpConnectionToProcessThread httpConnectionToProcessThread = new HttpConnectionToProcessThread(httpConnectionToProcess);
    Thread thread = new Thread(httpConnectionToProcessThread);
    thread.start();
  }
}
