import com.td.HttpServer.*;
import com.td.Mocks.*;
import java.util.HashMap;

public class HandlerPostFileContentsTest extends junit.framework.TestCase {

  MockFileIO mockFileIO;
  HandlerPostFileContents handler;
  byte[] body;
  String path;
  String path1;
  String path2;
  HttpRequest request;

  protected void setUp() {
    mockFileIO = new MockFileIO();
    body = "this".getBytes();
    path = "/testFile.txt";
    path1 = "/testFile-1.txt";
    path2 = "/testFile-2.txt";
    handler = new HandlerPostFileContents(mockFileIO);
    request = new HttpRequest("POST /testFile.txt HTTP/1.1", new HashMap<String, String>());
    System.out.println("******");
    System.out.println(request.path());
  }

  public void testHandlerWritesToFileThatDoesNotExsists() {
    handler.generateResponse(request);
    assert(mockFileIO.exists(path));
  }

  public void testHandlerWritesToNewFileIfPathExists() {
    mockFileIO.addToDirectoryContents(path);
    handler.generateResponse(request);
    assert(mockFileIO.exists(path1));
  }

  public void testGenerateInternalServerErrorResponseforIOException() {
    String path = "/throwIOException";
    HttpResponse response = handler.generateResponse(new HttpRequest("POST /throwIOException HTTP/1.1",  new HashMap<String, String>()));
    assertEquals(response.responseCode(), 500);
  }


  public void testHandlerWritesToNewFile2IfPathExists() {
    mockFileIO.addToDirectoryContents(path);
    mockFileIO.addToDirectoryContents(path1);
    handler.generateResponse(request);
    assert(mockFileIO.exists(path2));
  }

  public void testLocationHeader() {
    HttpResponse response = handler.generateResponse(request);
    assertEquals(response.getValueForHeader("Location"), path);
  }
}
