import com.td.HttpServer.*;
import com.td.Mocks.*;


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
  }

  private HttpResponse getResponse(String path) {
    handler = new HandlerGetDirectoryContents(path, mockFileIO, dirListHtml);
    return new HttpResponseBuilder().generateResponse(handler);
  }

  public void testResponseBodyContainsLinks() {
    String path = "/";
    response = getResponse(path);
    String body = new String(response.body());
    assert(body.contains("<a href=\"/file01\">file01</a>"));
  }

  public void testGenerateOkCode() {
    String path = "/";
    response = getResponse(path);
    assertEquals(200, response.responseCode());
  }

  public void testGenerateNotFoundCodeForIOException() {
    String path = "/throwIOException";
    response = getResponse(path);
    assertEquals(404, response.responseCode());
  }

  public void testContentType() {
    String path = "/";
    response = getResponse(path);
    assertEquals("text/html", response.getValueForHeader("Content-Type"));
  }
}
