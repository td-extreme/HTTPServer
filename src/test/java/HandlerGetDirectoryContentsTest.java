import com.td.HttpServer.*;
import com.td.Mocks.*;
import java.util.HashMap;

public class HandlerGetDirectoryContentsTest extends junit.framework.TestCase {

  HandlerGetDirectoryContents handler;
  HttpRequest request;
  HttpResponse response;
  DirListHtml dirListHtml;
  MockFileIO mockFileIO;
  int responseCode;
  HashMap<String, String> headers;


  protected void setUp() {
    dirListHtml = new DirListHtml();
    mockFileIO = new MockFileIO();
    mockFileIO.setIsFileFalse();
    mockFileIO.setIsDirectoryTrue();
    mockFileIO.addToDirectoryContents("file01");
  }

  public void testResponseBodyContainsLinks() {
    String path = "/";
    handler = new HandlerGetDirectoryContents(path, mockFileIO, dirListHtml);
    response = handler.generateResponse();
    String body = new String((byte[])response.call()[2]);
    assert(body.contains("<a href=\"/file01\">file01</a>"));
  }

  public void testGenerateOkCode() {
    String path = "/";
    handler = new HandlerGetDirectoryContents(path, mockFileIO, dirListHtml);
    response = handler.generateResponse();
    int responseCode = (int)response.call()[0];
    assertEquals(responseCode, 200);
  }

  public void testGenerateNotFoundCodeForIOException() {
    String path = "/throwIOException";
    handler = new HandlerGetDirectoryContents(path, mockFileIO, dirListHtml);
    response = handler.generateResponse();
    int responseCode = (int)response.call()[0];
    assertEquals(responseCode, 404);
  }

  public void testContentType() {
    String path = "/";
    handler = new HandlerGetDirectoryContents(path, mockFileIO, dirListHtml);
    response = handler.generateResponse();
    assertEquals("text/html", response.getValueForHeader("Content-Type"));
  }
}
