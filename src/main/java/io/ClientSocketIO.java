package com.td.HttpServer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

public class ClientSocketIO implements IClientSocketIO {

  private ServerSocket server;
  private Socket clientSocket;

  public ClientSocketIO(ServerSocket server) {
    this.server = server;
  }

  public String getRawRequestString() throws IOException {
    StringBuilder request = new StringBuilder();
    InputStreamReader isr = new InputStreamReader(clientSocket.getInputStream());
    BufferedReader reader = new BufferedReader(isr);
    String line = reader.readLine();
    while (!line.isEmpty()) {
      request.append(line);
      request.append("\n");
      line = reader.readLine();
    }
    return request.toString();
  }

  public byte[] getBytes(int length) throws IOException {
    InputStream stream = clientSocket.getInputStream();
    byte[] data = new byte[length];
    stream.read(data);
    return data;
  }

  public void sendString(String rawString) throws IOException {
    clientSocket.getOutputStream().write(rawString.getBytes("UTF-8"));
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

  public void sendBytes(byte[] bytes) throws IOException {
    clientSocket.getOutputStream().write(bytes);
  }
}
