import com.td.HttpServer.*;
import com.td.Mocks.*;
import java.util.*;
import java.io.IOException;

public class HandlerGetFileContentsTest extends junit.framework.TestCase {

  HandlerGetFileContents handler;
  ResponseHeadersForContent responseHeadersForContent;
  HttpRequest request;
  HttpResponse response;

  protected void setUp() {
    MockFileIO mockFileIO = new MockFileIO();
    responseHeadersForContent = new ResponseHeadersForContent();
    handler = new HandlerGetFileContents(mockFileIO, responseHeadersForContent);
    mockFileIO.setIsFileTrue();
    mockFileIO.setIsDirectoryFalse();
  }

  public void testGenerateOkResponseDefaultPath() {
    request = new HttpRequest("GET / HTTP/1.1");
    response = handler.generateResponse(request);
    assertEquals(response.responseCode(), 200);
  }

  public void testGenerateOkResponseValidFile() {
    request = new HttpRequest("GET /something.txt HTTP/1.1");
    response = handler.generateResponse(request);
    assertEquals(response.responseCode(), 200);
  }

  public void testGenerateNotFoundResponseforInvalidFile() {
    request = new HttpRequest("GET /throwIOException HTTP/1.1");
    response = handler.generateResponse(request);
    assertEquals(response.responseCode(), 404);
  }

  public void testContentTypeIsTextHtmlForTxtFile() {
    request = new HttpRequest("GET /something.txt HTTP/1.1");
    response = handler.generateResponse(request);
    assertEquals(response.headers().get("Content-Type"), "text/plain");
  }

  public void testContentTypeIsImageJpegForJpg() {
    request = new HttpRequest("GET /something.jpg HTTP/1.1");
    response = handler.generateResponse(request);
    assertEquals(response.headers().get("Content-Type"), "image/jpeg");
  }

  public void testContentTypeIsImageGifForGif() {
    request = new HttpRequest("GET /something.gif HTTP/1.1");
    response = handler.generateResponse(request);
    assertEquals(response.headers().get("Content-Type"), "image/gif");
  }

  public void testContentTypeIsPdfForPdf() {
    request = new HttpRequest("GET /something.pdf HTTP/1.1");
    response = handler.generateResponse(request);
    assertEquals(response.headers().get("Content-Type"), "application/pdf");
  }
}
