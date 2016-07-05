import com.td.HttpServer.*;
import java.util.*;
import java.io.IOException;

public class HandlerGetFileContentsTest extends junit.framework.TestCase {

  HandlerGetFileContents handler;
  ResponseHeadersForContent responseHeadersForContent;
  HttpRequest request;
  HttpResponse response;

  public class MockFileIO implements IFileIO {
    public String workingDirectory() { return "./"; }
    public boolean exists(String path) { return true; }
    public boolean isFile(String path) { return true; }
    public boolean isDirectory(String path) { return false; }
    public String[] getFiles(String directory) throws IOException {
      return new String[0];
    }
    public byte[] getContent(String fileName) throws IOException {
      if (fileName.equals("/throwIOException")) { throw new IOException(); }
      return new byte[0];
    }
  }

  protected void setUp() {
    MockFileIO mockFileIO = new MockFileIO();
    responseHeadersForContent = new ResponseHeadersForContent();
    handler = new HandlerGetFileContents(mockFileIO, responseHeadersForContent);
  }

  public void testGenerateOkResponseDefaultPath() {
    request = new HttpRequest("GET / HTTP/1.1");
    response = handler.generateResponse(request);
    assertEquals("HTTP/1.1 200 OK", response.responseLine());
  }

  public void testGenerateOkResponseValidFile() {
    request = new HttpRequest("GET /something.txt HTTP/1.1");
    response = handler.generateResponse(request);
    assertEquals("HTTP/1.1 200 OK", response.responseLine());
  }

  public void testGenerateNotFoundResponseforInvalidFile() {
    request = new HttpRequest("GET /throwIOException HTTP/1.1");
    response = handler.generateResponse(request);
    assertEquals("HTTP/1.1 404 Not Found", response.responseLine());
  }

  public void testContentTypeIsTextHtmlForTxtFile() {
    request = new HttpRequest("GET /something.txt HTTP/1.1");
    response = handler.generateResponse(request);
    assertTrue(response.headers().contains("text/plain"));
  }

  public void testContentTypeIsImageJpegForJpg() {
    request = new HttpRequest("GET /something.jpg HTTP/1.1");
    response = handler.generateResponse(request);
    assertTrue(response.headers().contains("image/jpeg"));
  }

  public void testContentTypeIsImageGifForGif() {
    request = new HttpRequest("GET /something.gif HTTP/1.1");
    response = handler.generateResponse(request);
    assertTrue(response.headers().contains("image/gif"));
  }

  public void testContentTypeIsPdfForPdf() {
    request = new HttpRequest("GET /something.pdf HTTP/1.1");
    response = handler.generateResponse(request);
    assertTrue(response.headers().contains("application/pdf"));
  }
}
