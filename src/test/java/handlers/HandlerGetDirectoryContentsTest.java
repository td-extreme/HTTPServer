import com.td.HttpServer.*;
import com.td.Mocks.*;
import java.util.HashMap;

public class HandlerGetDirectoryContentsTest extends junit.framework.TestCase {

  HandlerGetDirectoryContents handler;
  HttpRequest request;
  HttpResponse response;
  DirListHtml dirListHtml;
  MockFileIO mockFileIO;

  protected void setUp() {
    dirListHtml = new DirListHtml();
    mockFileIO = new MockFileIO();
    mockFileIO.setIsFileFalse();
    mockFileIO.setIsDirectoryTrue();
    mockFileIO.addToDirectoryContents("file01");
    handler = new HandlerGetDirectoryContents(mockFileIO, dirListHtml);
  }

  public void testResponseBodyContainsLinks() {
    response = handler.generateResponse(new HttpRequest("GET / HTTP/1.1", new HashMap<String, String>()));
    String body = new String(response.body());
    assert(body.contains("<a href=\"/file01\">file01</a>"));
  }

  public void testGenerateOkCode() {
    response = handler.generateResponse(new HttpRequest("GET / HTTP/1.1", new HashMap<String, String>()));
    assertEquals(200, response.responseCode());
  }

  public void testGenerateNotFoundCodeForIOException() {
    response = handler.generateResponse(new HttpRequest("GET /throwIOException HTTP/1.1", new HashMap<String, String>()));
    assertEquals(500, response.responseCode());
  }

  public void testContentType() {
    response = handler.generateResponse(new HttpRequest("GET / HTTP/1.1", new HashMap<String, String>()));
    assertEquals("text/html", response.getValueForHeader("Content-Type"));
  }
}
