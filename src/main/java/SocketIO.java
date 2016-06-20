package com.td.HttpServer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

public class SocketIO implements IMessageIO {

  ServerSocket server;
  Socket clientSocket;

  public SocketIO(int portNumber) throws IOException {
    server = new ServerSocket(portNumber);
  }

  public String getMessage() throws IOException {
    StringBuilder request = new StringBuilder();
    clientSocket = server.accept();
    InputStreamReader isr = new InputStreamReader(clientSocket.getInputStream());
    BufferedReader reader = new BufferedReader(isr);
    String line = reader.readLine();
    while (!line.isEmpty()) {
      request.append(line);
      request.append("\n");
      line = reader.readLine();
    }
    // Delete this line below
      System.out.println(request.toString());
    // Delete the line above
    return request.toString();
  }

  public void sendMessage(String message) throws IOException {
    clientSocket.getOutputStream().write(message.getBytes("UTF-8"));
    clientSocket.close();
  }
}
