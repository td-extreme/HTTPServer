import com.td.HttpServer.*;
import com.td.Mocks.*;
import java.util.*;
import java.io.IOException;

public class HandlerGetFileContentsTest extends junit.framework.TestCase {

  HandlerGetFileContents handler;
  HttpRequest request;
  HttpResponse response;
  MockFileIO mockFileIO;
  int responseCode;
  protected void setUp() {
    mockFileIO = new MockFileIO();
    mockFileIO.setIsFileTrue();
    mockFileIO.setIsDirectoryFalse();
  }

  public void testGenerateOkResponseDefaultPath() {
    String path = "/";
    handler = new HandlerGetFileContents(path, mockFileIO);
    response = handler.generateResponse();
    responseCode = (int)response.call()[0];
    assertEquals(responseCode, 200);
  }

  public void testGenerateOkResponseValidFile() {
    String path = "/something.txt";
    handler = new HandlerGetFileContents(path, mockFileIO);
    response = handler.generateResponse();
    responseCode = (int)response.call()[0];
    assertEquals(responseCode, 200);
  }

  public void testGenerateNotFoundResponseforInvalidFile() {
    String path = "/throwIOException";
    handler = new HandlerGetFileContents(path, mockFileIO);
    response = handler.generateResponse();
    responseCode = (int)response.call()[0];
    assertEquals(responseCode, 404);
  }

  public void testContentTypeIsTextHtmlForTxtFile() {
    String path = "/something.txt";
    handler = new HandlerGetFileContents(path, mockFileIO);
    response = handler.generateResponse();
    HashMap<String, String> headers = (HashMap<String, String>)response.call()[1];
    assertEquals(headers.get("Content-Type"), "text/plain");
  }
}
