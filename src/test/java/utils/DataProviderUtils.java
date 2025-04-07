package utils;

import org.testng.annotations.DataProvider;

public class DataProviderUtils {

    @DataProvider(name = "userData")
    public static Object[][] getUserData() {
        return new Object[][]{
                {"Alice", "Developer"},
                {"Bob", "Tester"},
                {"Charlie", "Manager"}
        };
    }
}
