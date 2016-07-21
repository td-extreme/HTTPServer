package com.td.HttpServer;

public class HttpConnectionToProcessThread implements Runnable {
  private HttpConnectionToProcess httpConnectionToProcess;
  public HttpConnectionToProcessThread(HttpConnectionToProcess httpConnectionToProcess) {
  this.httpConnectionToProcess = httpConnectionToProcess;
  }
  public void run() {
    httpConnectionToProcess.execute();
  }
}
