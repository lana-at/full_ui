package tests;

import io.qameta.allure.Description;
import org.junit.jupiter.api.Test;
import testdata.TestData;

public class SearchTest extends BaseTest {

    @Test
    @Description("Поиск решения Windows Server")
    void searchWindowsServerTest() {
        mainPage.open()
                .openSearch()
                .searchFor(TestData.SEARCH_QUERY);
    }
}