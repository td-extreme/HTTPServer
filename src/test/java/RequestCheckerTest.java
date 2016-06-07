public class RequestCheckerTest extends junit.framework.TestCase {
 
  RequestChecker checker;

  protected void setUp() {
    checker = new RequestChecker();
  }

  // Tesing invalid requests 
  public void testInvalidRequestType() {
    assertFalse(checker.isValidHTTP("DANCE / HTTP/1.1"));
  }
 
  public void testInvalidHTTPType() { 
    assertFalse(checker.isValidHTTP("GET / http/1.1"));
  } 

  public void testInvalidPath() {
    assertFalse(checker.isValidHTTP("GET \\ HTTP/1.1"));
  }

  // Testing for each valid request type  
  public void testForGetRequest() {
    assertTrue(checker.isValidHTTP("GET / HTTP/1.1"));
  }

  public void testForHeadRequest() {
    assertTrue(checker.isValidHTTP("HEAD / HTTP/1.1"));
  }

  public void testForPostRequest() {
    assertTrue(checker.isValidHTTP("POST / HTTP/1.1"));
  }
  
  public void testForPutRequest() {
    assertTrue(checker.isValidHTTP("PUT / HTTP/1.1"));
  }
   
  public void testForDeleteRequest() {
    assertTrue(checker.isValidHTTP("DELETE / HTTP/1.1"));
  }
  
  public void testForTraceRequest() {
    assertTrue(checker.isValidHTTP("TRACE / HTTP/1.1"));
  }

  public void testForOptionsRequest() {
    assertTrue(checker.isValidHTTP("OPTIONS / HTTP/1.1"));
  }
  
  public void testForConnectRequest() {
    assertTrue(checker.isValidHTTP("CONNECT / HTTP/1.1"));
  }
   
  public void testForPatchRequest() {
    assertTrue(checker.isValidHTTP("PATCH / HTTP/1.1"));
  }
}
