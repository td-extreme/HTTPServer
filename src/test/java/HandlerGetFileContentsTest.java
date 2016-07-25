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
  }

  private HttpResponse getResponse(String path) {
    handler = new HandlerGetFileContents(path, mockFileIO);
    return new HttpResponseBuilder().generateResponse(handler);
  }

  public void testGenerateOkResponseDefaultPath() {
    String path = "/";
    response = getResponse(path);
    assertEquals(response.responseCode(), 200);
  }

  public void testGenerateOkResponseValidFile() {
    String path = "/something.txt";
    response = getResponse(path);
    assertEquals(response.responseCode(), 200);
  }

  public void testGenerateNotFoundResponseforInvalidFile() {
    String path = "/throwIOException";
    response = getResponse(path);
    assertEquals(response.responseCode(), 404);
  }

  public void testContentTypeIsTextHtmlForTxtFile() {
    String path = "/something.txt";
    response = getResponse(path);
    assertEquals(response.headers().get("Content-Type"), "text/plain");
  }
}
