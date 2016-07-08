import com.td.HttpServer.*;
import com.td.Mocks.*;
import java.util.*;
import java.io.IOException;

public class HandlerGetFileContentsTest extends junit.framework.TestCase {

  HandlerGetFileContents handler;
  ResponseHeadersForContent responseHeadersForContent;
  HttpRequest request;
  HttpResponse response;
  MockFileIO mockFileIO;

  protected void setUp() {
    mockFileIO = new MockFileIO();
    responseHeadersForContent = new ResponseHeadersForContent();
    mockFileIO.setIsFileTrue();
    mockFileIO.setIsDirectoryFalse();
  }

  public void testGenerateOkResponseDefaultPath() {
    String path = "/";
    handler = new HandlerGetFileContents(path, mockFileIO, responseHeadersForContent);
    response = handler.generateResponse();
    assertEquals(response.responseCode(), 200);
  }

  public void testGenerateOkResponseValidFile() {
    String path = "/something.txt";
    handler = new HandlerGetFileContents(path, mockFileIO, responseHeadersForContent);
    response = handler.generateResponse();
    assertEquals(response.responseCode(), 200);
  }

  public void testGenerateNotFoundResponseforInvalidFile() {
    String path = "/throwIOException";
    handler = new HandlerGetFileContents(path, mockFileIO, responseHeadersForContent);
    response = handler.generateResponse();
    assertEquals(response.responseCode(), 404);
  }

  public void testContentTypeIsTextHtmlForTxtFile() {
    String path = "/something.txt";
    handler = new HandlerGetFileContents(path, mockFileIO, responseHeadersForContent);
    response = handler.generateResponse();
    assertEquals(response.headers().get("Content-Type"), "text/plain");
  }

  public void testContentTypeIsImageJpegForJpg() {
    String path = "/something.jpg";
    handler = new HandlerGetFileContents(path, mockFileIO, responseHeadersForContent);
    response = handler.generateResponse();
    assertEquals(response.headers().get("Content-Type"), "image/jpeg");
  }

  public void testContentTypeIsImageGifForGif() {
    String path = "/something.gif";
    handler = new HandlerGetFileContents(path, mockFileIO, responseHeadersForContent);
    response = handler.generateResponse();
    assertEquals(response.headers().get("Content-Type"), "image/gif");
  }

  public void testContentTypeIsPdfForPdf() {
    String path = "/something.pdf";
    handler = new HandlerGetFileContents(path, mockFileIO, responseHeadersForContent);
    response = handler.generateResponse();
    assertEquals(response.headers().get("Content-Type"), "application/pdf");
  }
}
