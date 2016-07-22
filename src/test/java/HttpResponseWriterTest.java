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
    response = new HttpResponse();
  }

  public void testGeneratedResponseSentOutToSocket() throws BadConnectionException {
    response.setBody("This is the response body");
    response.addHeader("Test/Header", "test value");
    response.addHeader("Test/Header2", "test value2");
    String responseShouldBe = "HTTP/1.1 200 OK\r\nTest/Header: test value\r\nTest/Header2: test value2\r\nContent-Length: 25\r\n\r\nThis is the response body";
    writer.sendHttpResponse(mockSocketIO, response);
    String responseGenerated = new String(mockSocketIO.getReceivedBytes());
    assertEquals(responseShouldBe, responseGenerated);
  }
}
