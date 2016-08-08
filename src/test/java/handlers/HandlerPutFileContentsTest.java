import com.td.HttpServer.*;
import com.td.Mocks.*;
import java.util.HashMap;

public class HandlerPutFileContentsTest extends junit.framework.TestCase {

  MockFileIO mockFileIO;
  HandlerPutFileContents handler;
  byte[] body;
  String path;
  HttpRequest request;
  HttpResponse response;

  protected void setUp() {
    mockFileIO = new MockFileIO();
    body = "this".getBytes();
    path = "/test.txt";
    handler = new HandlerPutFileContents(mockFileIO);
    request = new HttpRequest("PUT /test.txt HTTP/1.1", new HashMap<String, String>());
    response = handler.generateResponse(request);
  }

  public void testReponseCodeIs200() {
    assertEquals(200, response.responseCode());
  }

  public void testWhenRequestIsCalledItWritesToFile() {
    assert(mockFileIO.exists(path));
  }
}
