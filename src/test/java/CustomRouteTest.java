import com.td.HttpServer.CustomRoute;
import com.td.Mocks.MockHandler;

public class CustomRouteTest extends junit.framework.TestCase {

  private CustomRoute testRoute;
  private MockHandler mockHandler;

  protected void setUp() {
    mockHandler = new MockHandler();
    testRoute = new CustomRoute("GET", "/this", mockHandler);
  }

  public void testMatchMethodReturnsTrueForMatch() {
    assert(testRoute.match("GET", "/this"));
  }

  public void testMatchMethodReturnsFalseForNonMatchMethod() {
    assertFalse(testRoute.match("POST", "/this"));
  }

  public void testMatchMethodReturnsFalseForNonMatchPath() {
    assertFalse(testRoute.match("GET", "/that"));
  }
}
