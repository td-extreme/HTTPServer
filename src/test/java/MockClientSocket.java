package com.td.Mocks;
import com.td.HttpServer.*;

public class MockClientSocket implements IClientSocketIO {
  private long closedTime;
  private int sleepTime;

  public MockClientSocket(int sleepTime) {
    this.sleepTime = sleepTime;
  }

  public void sleep() {
    try {
      Thread.sleep(sleepTime);
    }
    catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

  public Long getClosedTime() {
    return closedTime;
  }

  public void closeClientConnection() {
    sleep();
    closedTime = System.currentTimeMillis();
  }

  public void openClientConnection() throws BadConnectionException {}

  public IClientSocketInput clientSocketInput() {
    return new MockClientSocketInput("GET / HTTP/1.1\r\n\r\n", new byte[0]);
  }

  public IClientSocketOutput clientSocketOutput() {
    return new MockClientSocketOutput();
  }
}


