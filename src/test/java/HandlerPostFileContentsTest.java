import com.td.HttpServer.*;
import com.td.Mocks.*;

public class HandlerPostFileContentsTest extends junit.framework.TestCase {

  MockFileIO mockFileIO;
  HandlerPostFileContents handler;
  byte[] body;
  String path;
  String path1;
  String path2;

  protected void setUp() {
    mockFileIO = new MockFileIO();
    body = "this".getBytes();
    path = "testFile.txt";
    path1 = "testFile-1.txt";
    path2 = "testFile-2.txt";
    handler = new HandlerPostFileContents(path, body, mockFileIO);
  }

  public void testHandlerWritesToFileThatDoesNotExsists() {
    handler.generateResponse();
    assert(mockFileIO.exists(path));
  }

  public void testHandlerWritesToNewFileIfPathExists() {
    mockFileIO.addToDirectoryContents(path);
    handler.generateResponse();
    assert(mockFileIO.exists(path1));
  }

  public void testHandlerWritesToNewFile2IfPathExists() {
    mockFileIO.addToDirectoryContents(path);
    mockFileIO.addToDirectoryContents(path1);
    handler.generateResponse();
    assert(mockFileIO.exists(path2));
  }

  public void testLocationHeader() {
    HttpResponse response = handler.generateResponse();
    assertEquals(response.getValueForHeader("Location"), path);
  }
}
