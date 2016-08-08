package com.td.HttpServer;

public interface IClientSocketIO {
  public void closeClientConnection();
  public void openClientConnection() throws BadConnectionException;
  public IClientSocketOutput clientSocketOutput();
  public IClientSocketInput clientSocketInput() throws BadConnectionException;
}
