import com.td.HttpServer.*;
import com.td.Mocks.*;
import java.util.*;
import java.io.IOException;

public class HandlerGetFileContentsTest extends junit.framework.TestCase {

  HandlerGetFileContents handler;
  HttpRequest request;
  HttpResponse response;
  MockFileIO mockFileIO;

  protected void setUp() {
    mockFileIO = new MockFileIO();
    mockFileIO.setIsFileTrue();
    mockFileIO.setIsDirectoryFalse();
    handler = new HandlerGetFileContents(mockFileIO);
  }

  public void testGenerateOkResponseDefaultPath() {
    String path = "/";
    response = handler.generateResponse(new HttpRequest("GET / HTTP/1.1", new HashMap<String, String>()));
    assertEquals(response.responseCode(), 200);
  }

  public void testGenerateOkResponseValidFile() {
    String path = "/something.txt";
    response = handler.generateResponse(new HttpRequest("GET /something.txt HTTP/1.1", new HashMap<String, String>()));
    assertEquals(response.responseCode(), 200);
  }

  public void testGenerateNotFoundResponseforInvalidFile() {
    String path = "/throwIOException";
    response = handler.generateResponse(new HttpRequest("GET /throwIOException HTTP/1.1", new HashMap<String, String>()));
    assertEquals(response.responseCode(), 500);
  }

  public void testContentTypeIsTextHtmlForTxtFile() {
    String path = "/something.txt";
    response = handler.generateResponse(new HttpRequest("GET /something.txt HTTP/1.1", new HashMap<String, String>()));
    assertEquals(response.headers().get("Content-Type"), "text/plain");
  }
}
