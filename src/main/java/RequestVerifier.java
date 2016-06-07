public class RequestVerifier {

  public boolean isValidHTTP(String request) {
    String request_array[] = request.split(" ");
    return isFirstParamaterValid(request_array[0]) &&
           isSecondParamaterValid(request_array[1]) &&
           isThirdParameterValid(request_array[2]);
  }

  private boolean isFirstParamaterValid(String first) {
    return isRequestGet(first) ||
           isRequestHead(first) ||
           isRequestPost(first) ||
           isRequestPut(first) ||
           isRequestDelete(first) ||
           isRequestTrace(first) ||
           isRequestOptions(first) ||
           isRequestConnect(first) ||
           isRequestPatch(first);
  }

  private boolean isSecondParamaterValid(String second) {
    return second.substring(0, 1).equals("/");
  }

  private boolean isThirdParameterValid(String third) {
    return third.equals("HTTP/1.1");
  }

  private boolean isRequestGet(String request) {
    return request.equals("GET");
  }

  private boolean isRequestHead(String request) {
    return request.equals("HEAD");
  }

  private boolean isRequestPost(String request) {
    return request.equals("POST");
  }

  private boolean isRequestPut(String request) {
    return request.equals("PUT");
  }

  private boolean isRequestDelete(String request) {
    return request.equals("DELETE");
  }

  private boolean isRequestTrace(String request) {
    return request.equals("TRACE");
  }

  private boolean isRequestOptions(String request) {
    return request.equals("OPTIONS");
  }

  private boolean isRequestConnect(String request) {
    return request.equals("CONNECT");
  }

  private boolean isRequestPatch(String request) {
    return request.equals("PATCH");
  }
}
