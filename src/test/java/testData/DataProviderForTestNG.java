package testData;

import org.testng.annotations.DataProvider;

public class DataProviderForTestNG {
    @DataProvider(name = "testng")
    public static Object[][] getTestDataForTestNG() {
        return new Object[][]{
                {"total", 20},
                {"failed", 8},
                {"passed", 10},
                {"skipped", 2}
        };
    }
}
