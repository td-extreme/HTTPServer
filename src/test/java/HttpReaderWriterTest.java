import com.td.HttpServer.*;
import java.io.IOException;

class TestSocketValid implements IMessageIO {
  public String getMessage() {
    return "GET / HTTP/1.1";
  }
  public void sendMessage(String dummy) { }
}

class TestSocketInvalid implements IMessageIO {
  private int count = 0;
  private String receivedMessage;
  public String getMessage() {
    if (count == 0) {
      count++;
      return "NOT valid";
    }
      return "GET / HTTP/1.1";
  }
  public void sendMessage(String message) {
    receivedMessage = message;
  }
  public boolean receivedBadConnection() {
    return (receivedMessage.contains("400"));
  }
}

public class HttpReaderWriterTest extends junit.framework.TestCase{

  IValidator httpValidator;
  HttpReaderWriter httpReaderWriter;

  protected void setUp() {
    httpValidator = new HttpVerifier();
  }

  public void testGetValidHttpRequest() throws IOException {
    httpReaderWriter = new HttpReaderWriter(new TestSocketValid(), httpValidator);
    HttpRequest request = httpReaderWriter.getHttpRequest();
    assertEquals("GET / HTTP/1.1", request.requestLine());
  }

  public void testGetInvalidHttpRequest() throws IOException {
    TestSocketInvalid testSocket = new TestSocketInvalid();
    httpReaderWriter = new HttpReaderWriter(testSocket, httpValidator);
    HttpRequest request = httpReaderWriter.getHttpRequest();
    assertTrue(testSocket.receivedBadConnection());
  }
  // TODO write test to check that Response is sent
}
