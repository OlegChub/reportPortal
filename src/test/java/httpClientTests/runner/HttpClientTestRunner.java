package httpClientTests.runner;

import org.junit.platform.suite.api.IncludeTags;
import org.junit.platform.suite.api.SelectPackages;
import org.junit.platform.suite.api.Suite;
import org.junit.platform.suite.api.SuiteDisplayName;

@Suite
@SuiteDisplayName("HttpClient tests")
@SelectPackages("httpClientTests")
@IncludeTags("httpClientTests")
public class HttpClientTestRunner {
}
