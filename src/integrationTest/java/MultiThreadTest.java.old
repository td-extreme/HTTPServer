import com.td.HttpServer.*;
import java.io.IOException;
import java.lang.InterruptedException;
import java.util.HashMap;

public class MultiThreadTest extends junit.framework.TestCase {

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

    public String getRawRequestString() throws IOException {
      sleep();
      return "";
    }

    public byte[] getBytes(int length) throws IOException {
      return new byte[0];
    }
    public void closeClientConnection() {
      closedTime = System.currentTimeMillis();
    }

    public void openClientConnection() throws BadConnectionException {}
    public void sendString(String rawString) throws IOException {}
    public void sendBytes(byte[] bytes) throws IOException {}
  }

  public class MockHandlerRouter implements IHandlerRouter {
    public Ihandler selectHandlerBadRequest() {
      return new MockHandler();
    }
    public Ihandler selectHandler(HttpRequest request) {
      return new MockHandler();
    }
  }

  public class MockRequestBuiler implements IRequestBuilder {
    public HttpRequest getNextRequest(IClientSocketInput client) throws InvalidHttpRequestException, BadConnectionException {
      try {
      client.getRawRequestString();
      }
      catch (IOException e) {
        e.printStackTrace();
      }
      return new HttpRequest("Dummy", new HashMap<String, String>());
    }
  }

  public class MockResponseWriter implements IResponseWriter {
    public void sendHttpResponse(IClientSocketOutput client, HttpResponse response) throws BadConnectionException {
    }
  }

  public class MockHandler implements Ihandler {
    public HttpResponse generateResponse() {
      return new HttpResponse();
    }
  }

  public void testThreadOneFinishedBeforeThreadZero() {
    MockClientSocket client0 = new MockClientSocket(15);
    MockClientSocket client1 = new MockClientSocket(0);
    MockHandlerRouter mockHandlerRouter = new MockHandlerRouter();
    MockRequestBuiler mockRequestBuilder = new MockRequestBuiler();
    MockResponseWriter mockResponseWriter = new MockResponseWriter();
    HttpServerThread serverThread0 = new HttpServerThread(client0, mockHandlerRouter, mockRequestBuilder, mockResponseWriter);
    HttpServerThread serverThread1 = new HttpServerThread(client1, mockHandlerRouter, mockRequestBuilder, mockResponseWriter);
    Thread thread0 = new Thread(serverThread0);
    Thread thread1 = new Thread(serverThread0);
    thread0.start();
    thread1.start();
    try {
      Thread.sleep(20);
    }
    catch (InterruptedException e) {
      e.printStackTrace();
    }
    assert(client0.getClosedTime() > client1.getClosedTime());
  }
}
