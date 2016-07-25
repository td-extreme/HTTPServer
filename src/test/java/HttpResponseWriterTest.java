import com.td.Mocks.MockClientSocketOutput;
import com.td.HttpServer.*;
import java.util.*;
import java.io.IOException;

public class HttpResponseWriterTest extends junit.framework.TestCase {
  private MockClientSocketOutput mockSocketIO;
  private HttpResponse response;
  private HttpResponseWriter writer;

  protected void setUp() {
    mockSocketIO = new MockClientSocketOutput();
    writer = new HttpResponseWriter();
    HashMap<String, String> headers = new HashMap<String, String>();
    headers.put("Content-Type", "text/plain");
    byte[] body = "This is the response body".getBytes();
    response = new HttpResponse(200, headers, body);
  }

  public void testGeneratedResponseSentOutToSocket() throws BadConnectionException {
    String responseShouldBe = "HTTP/1.1 200 OK\r\nContent-Length: 25\r\nContent-Type: text/plain\r\n\r\nThis is the response body";
    writer.sendHttpResponse(mockSocketIO, response);
    String responseGenerated = new String(mockSocketIO.getReceivedBytes());
    assertEquals(responseShouldBe, responseGenerated);
  }
}
