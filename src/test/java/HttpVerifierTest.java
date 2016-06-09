import com.td.HttpServer.*;

public class HttpVerifierTest extends junit.framework.TestCase {

  HttpVerifier checker;

  protected void setUp() {
    checker = new HttpVerifier();
  }

  // Tesing invalid requests
  public void testInvalidRequestType() {
    assertFalse(checker.isValid("DANCE / HTTP/1.1"));
  }

  public void testInvalidHTTPType() {
    assertFalse(checker.isValid("GET / http/1.1"));
  }

  public void testInvalidPath() {
    assertFalse(checker.isValid("GET \\ HTTP/1.1"));
  }

  // Test that a route can appear after the /
  public void testRoute() {
    assertTrue(checker.isValid("GET /route HTTP/1.1"));
  }

  public void testRoute2() {
    assertTrue(checker.isValid("GET /favicon.ico HTTP/1.1\nSome more stuff"));
  }


  // Testing for each valid request type
  public void testForGetRequest() {
    assertTrue(checker.isValid("GET / HTTP/1.1"));
  }

  public void testForHeadRequest() {
    assertTrue(checker.isValid("HEAD / HTTP/1.1"));
  }

  public void testForPostRequest() {
    assertTrue(checker.isValid("POST / HTTP/1.1"));
  }

  public void testForPutRequest() {
    assertTrue(checker.isValid("PUT / HTTP/1.1"));
  }

  public void testForDeleteRequest() {
    assertTrue(checker.isValid("DELETE / HTTP/1.1"));
  }

  public void testForTraceRequest() {
    assertTrue(checker.isValid("TRACE / HTTP/1.1"));
  }

  public void testForOptionsRequest() {
    assertTrue(checker.isValid("OPTIONS / HTTP/1.1"));
  }

  public void testForConnectRequest() {
    assertTrue(checker.isValid("CONNECT / HTTP/1.1"));
  }

  public void testForPatchRequest() {
    assertTrue(checker.isValid("PATCH / HTTP/1.1"));
  }
}
