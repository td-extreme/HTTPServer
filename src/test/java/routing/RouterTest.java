import java.util.HashMap;
import com.td.HttpServer.Router;
import com.td.HttpServer.DefaultHandlerRouter;
import com.td.HttpServer.IHandler;
import com.td.HttpServer.IHandlerRouter;
import com.td.HttpServer.HttpRequest;
import com.td.Mocks.MockFileIO;
import com.td.test.CustomRouter;

public class RouterTest extends junit.framework.TestCase {

  private Router router;
  private DefaultHandlerRouter defaultHandlerRouter;
  private IHandlerRouter customHandlerRouter;
  private MockFileIO mockFileIO;
  private HttpRequest request;

  protected void setUp() {
    mockFileIO = new MockFileIO();
    defaultHandlerRouter = new DefaultHandlerRouter(mockFileIO);
    customHandlerRouter = new CustomRouter();
    router = new Router(defaultHandlerRouter);
    mockFileIO.setIsDirectoryTrue();
  }

  public void testDefaultPathIsSelectedFromDefaultHandler() {
    buildRequest("GET / HTTP/1.1");
    assertEquals(getType(), "HandlerGetDirectoryContents");
  }

  public void testCustomRouterOverridesDefaultPath() {
    router.setCustomHandlerRouter(customHandlerRouter);
    buildRequest("GET / HTTP/1.1");
    assertEquals(getType(), "MockHandler");
  }

  public void testRouteIsSelectedFromDefaultHandlerIfNotDefinedInCustomRouter() {
    router.setCustomHandlerRouter(customHandlerRouter);
    buildRequest("GET /file HTTP/1.1");
    assertEquals(getType(), "HandlerGetDirectoryContents");
  }

  private void buildRequest(String requestLine) {
    request = new HttpRequest(requestLine, new HashMap<String, String>());
  }

  private String getType() {
    return router.selectHandler(request).getClass().getSimpleName();
  }
}
