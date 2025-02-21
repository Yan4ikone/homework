import io.qameta.allure.Feature;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import pages.MainMarketAfterSearch;
import pages.MainMarketBeforeSearch;

public class Tests extends BaseTest {


@Feature("Проверка перехода во вкладку ноутбуки")
@DisplayName("Проверка результатов перехода")
@ParameterizedTest(name = "{displayName} : {arguments}")
@MethodSource(helpers.DataProvider)
    public void testMainPageYandex(){
    chromeDriver.get("https://market.yandex.ru/");
    MainMarketBeforeSearch mainMarketBeforeSearch = new MainMarketBeforeSearch(chromeDriver);
    mainMarketBeforeSearch.find();
    MainMarketAfterSearch mainMarketAfterSearch = new MainMarketAfterSearch(chromeDriver);

    }
}
