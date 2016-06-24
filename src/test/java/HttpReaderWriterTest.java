import com.td.HttpServer.*;
import java.io.IOException;

class TestSocketValid implements IMessageIO {
  public String getMessage() {
    return "GET / HTTP/1.1\r\n\r\n";
  }
  public void sendMessage(String dummy) { }
  public void sendBytes(byte[] bytes) { }
  public void closeClientConnection() { }
  public void openClientConnection() { }
}

class TestSocketInvalid implements IMessageIO {
  private int count = 0;
  private String receivedMessage;
  public String getMessage() {
    if (count == 0) {
      count++;
      return "NOT valid";
    }
      return "GET / HTTP/1.1\r\n\r\n";
  }
  public void sendMessage(String message) {
    receivedMessage = message;
  }
  public boolean receivedBadConnection() {
    return (receivedMessage.contains("400"));
  }
  public void closeClientConnection() { }
  public void openClientConnection() { }
  public void sendBytes(byte[] bytes) { }
}

public class HttpReaderWriterTest extends junit.framework.TestCase{

  IValidator httpValidator;
  HttpReaderWriter httpReaderWriter;

  protected void setUp() {
    httpValidator = new HttpVerifier();
  }

  public void testGetValidHttpRequest() throws IOException {
    httpReaderWriter = new HttpReaderWriter(new TestSocketValid(), new HttpRequestBuilder(httpValidator));
    HttpRequest request = httpReaderWriter.getHttpRequest();
    assertEquals("GET / HTTP/1.1", request.requestLine());
  }

  public void testGetInvalidHttpRequest() throws IOException {
    TestSocketInvalid testSocket = new TestSocketInvalid();
    httpReaderWriter = new HttpReaderWriter(testSocket,new HttpRequestBuilder(httpValidator));
    HttpRequest request = httpReaderWriter.getHttpRequest();
    assertTrue(testSocket.receivedBadConnection());
  }
}
