package com.td.HttpServer;
import java.io.IOException;
import java.net.Socket;


public class ClientSocketOutput implements IClientSocketOutput {
  private Socket clientSocket;

  public ClientSocketOutput(Socket clientSocket) {
    this.clientSocket = clientSocket;
  }

  public void sendString(String rawString) throws IOException {
    clientSocket.getOutputStream().write(rawString.getBytes("UTF-8"));
  }

  public void sendBytes(byte[] bytes) throws IOException {
    clientSocket.getOutputStream().write(bytes);
  }
}
