import io.qameta.allure.Feature;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import pages.MainMarketAfterSearch;
import pages.MainMarketBeforeSearch;

public class Tests extends BaseTest {


@Feature("Проверка перехода во вкладку ноутбуки")
@DisplayName("Проверка результатов перехода")
@ParameterizedTest(name = "{displayName} : {arguments}")
@CsvSource({"market,laptop", "market,market", "не отработал,плохо"})
    public void testMainPageYandex(String title, String result){
    chromeDriver.get("https://market.yandex.ru/");
    MainMarketBeforeSearch mainMarketBeforeSearch = new MainMarketBeforeSearch(chromeDriver);
    mainMarketBeforeSearch.find(title);
    MainMarketAfterSearch mainMarketAfterSearch = new MainMarketAfterSearch(chromeDriver);
    Assertions.assertTrue(mainMarketAfterSearch.getTitle().contains(result), "Title is not correct " + title + " is your title" );


    }
}
