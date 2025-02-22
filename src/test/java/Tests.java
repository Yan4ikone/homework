import io.qameta.allure.Feature;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.openqa.selenium.support.PageFactory;
import pages.MainMarketAfterSearch;
import pages.MainMarketBeforeSearch;
import pages.PageFactoryMainMarket;

public class Tests extends BaseTest {


@Feature("Проверка перехода во вкладку ноутбуки")
@DisplayName("Проверка результатов перехода")
@ParameterizedTest(name = "{displayName} : {arguments}")
@CsvSource({"market,laptop", "market,market", "не отработал,плохо"})
    public void testMainPageYandex(String word, String result){
    chromeDriver.get("https://market.yandex.ru/");
    PageFactoryMainMarket pageFactoryMainMarket = PageFactory.initElements(chromeDriver,PageFactoryMainMarket.class);
    pageFactoryMainMarket.find(word);
    Assertions.assertTrue(pageFactoryMainMarket., "Title is not correct " + word + " is your title" );


    }
}
