import com.td.HttpServer.*;

public class ArgumentParserTest extends junit.framework.TestCase {

  ArgumentParser arguments;
  String[] args;
  ArgumentParser noArguments;
  String[] noArgs;

  protected void setUp() {
    args = new String[] { "-pn", "1234" };
    arguments = new ArgumentParser(args);
    noArgs = new String[] {};
    noArguments = new ArgumentParser(noArgs);
  }

  public void testCustomPortNumberArgument() {
    assertEquals(arguments.getPortNumber(), 1234);
  }

  public void testDefaultPortNumberArgument() {
    assertEquals(noArguments.getPortNumber(), 8080);
  }
}
