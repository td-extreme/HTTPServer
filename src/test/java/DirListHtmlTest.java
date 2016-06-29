import com.td.HttpServer.*;

public class DirListHtmlTest {

  DirListHtml dirListHtml;
  String pathRoot;
  String path;
  String[] mockFiles;
  String builtHtmlPage;
  String builtHtmlPageRootDirForPath;

  protected void setUp() {
    dirListHtml = new DirListHtml();
    pathRoot = "/";
    path = "myPath";
    mockFiles = new String[] { "File01.txt", "directory01" };
    builtHtmlPage = new String(dirListHtml.buildHtmlPage(path, mockFiles));
    builtHtmlPageRootDirForPath = new String(dirListHtml.buildHtmlPage(pathRoot, mockFiles));
  }

  public void testPageHasDocTypeTag() {
    assert(builtHtmlPage.contains("<!DOCTYPE html>"));
  }

  public void testPageHasHtmlTag() {
    assert(builtHtmlPage.contains("<HTML>"));
  }

  public void testPageHasHtmlClosingTag() {
    assert(builtHtmlPage.contains("</HTML>"));
  }

  public void testPageHasBodyTag() {
    assert(builtHtmlPage.contains("<BODY>"));
  }

  public void testPageHasBodyClosingTag() {
    assert(builtHtmlPage.contains("</BODY>"));
  }

  public void testPageHasLinkToFile() {
    assert(builtHtmlPage.contains("<a href=\"myPath/File01.txt\">File01.txt</a>"));
  }

  public void testWhenPathIsRootDirectory() {
    assert(builtHtmlPageRootDirForPath.contains("<a href=\"/File01.txt\">File01.txt</a>"));
  }
}
