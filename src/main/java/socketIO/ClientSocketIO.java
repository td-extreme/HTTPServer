package com.td.HttpServer;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ClientSocketIO implements IClientSocketIO {

  private ServerSocket server;
  private Socket clientSocket;

  public ClientSocketIO(ServerSocket server) {
    this.server = server;
  }

  public void openClientConnection() throws BadConnectionException {
    try {
    clientSocket = server.accept();
    }
    catch (IOException e) {
      throw new BadConnectionException(e);
    }
  }

  public void closeClientConnection() {
    try {
    clientSocket.close();
    }
    catch (IOException e) {
      e.printStackTrace();
    }
  }

  public IClientSocketInput clientSocketInput() throws BadConnectionException {
    try {
    return new ClientSocketInput(clientSocket);
    }
    catch (IOException e) {
      e.printStackTrace();
      throw new BadConnectionException(e);
    }
  }

  public IClientSocketOutput clientSocketOutput() {
    return new ClientSocketOutput(clientSocket);
  }
}
