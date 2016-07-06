import com.td.HttpServer.*;
import com.td.Mocks.*;


public class HandlerGetDirectoryContentsTest extends junit.framework.TestCase {

  HandlerGetDirectoryContents handler;
  HttpRequest request;
  HttpResponse response;
  DirListHtml dirListHtml;


  protected void setUp() {
    dirListHtml = new DirListHtml();
    MockFileIO mockFileIO = new MockFileIO();
    mockFileIO.setIsFileFalse();
    mockFileIO.setIsDirectoryTrue();
    String[] contents = { "file01", "file02", "file03" };
    mockFileIO.setDirectoryContents(contents);
    handler = new HandlerGetDirectoryContents(mockFileIO, dirListHtml);
  }

  public void testResponseBodyContainsLinks() {
    request = new HttpRequest("GET / HTTP/1.1");
    response = handler.generateResponse(request);
    String body = new String(response.body());
    System.out.println(body);
    assert(body.contains("<a href=\"/file01\">file01</a>"));
  }

  public void testGenerateOkCode() {
    request = new HttpRequest("GET / HTTP/1.1");
    response = handler.generateResponse(request);
    assertEquals(200, response.getResponseCode());
  }

  public void testGenerateNotFoundCodeForIOException() {
    request = new HttpRequest("GET /throwIOException HTTP/1.1");
    response = handler.generateResponse(request);
    assertEquals(404, response.getResponseCode());
  }
}
