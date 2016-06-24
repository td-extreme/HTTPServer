import com.td.HttpServer.*;

public class ArgumentParserTest extends junit.framework.TestCase {

  ArgumentParser arguments;
  String[] args;
  ArgumentParser noArguments;
  String[] noArgs;

  protected void setUp() {
    args = new String[] { "-p", "1234", "-d", "./path" };
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

  public void testCustomDirectoryPath() {
    assertEquals(arguments.getDirectory(), "./path");
  }

  public void testDefaultDirectoryPath() {
    assertEquals(noArguments.getDirectory(), "./");
  }
}
