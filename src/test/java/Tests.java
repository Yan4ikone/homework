import io.qameta.allure.Feature;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import pages.PageFactoryLaptopsAfterSearch;
import pages.PageFactoryLaptopsMarket;
import pages.PageFactoryMainMarket;
import static helpers.Properties.configProperties;
import static steps.StepsAll.*;

import java.util.List;

public class Tests extends BaseTest {


    @Feature("Проверка перехода во вкладку ноутбуки")
    @DisplayName("Проверка результатов перехода")
    @ParameterizedTest(name = "{displayName} : {arguments}")
    @CsvSource({"Ноутбуки,Ноутбуки", "Аксессуары,Ноутбуки", "не отработал,Ноутбуки"})
    public void testMainPageYandex(String word, String result){
        chromeDriver.get(configProperties.baseUrl());
        PageFactoryMainMarket pageFactoryMainMarket = PageFactory.initElements(chromeDriver,PageFactoryMainMarket.class);
        pageFactoryMainMarket.find();
       // Assertions.assertTrue(pageFactoryMainMarket.getHeaderAfterClick(result).getText().contains(word), "Title is not correct " + result + " - is your title");

    }

    @Feature("Проверка совпадения количества элементов в поиске")
    @DisplayName("Проверка количества элементов")
    @ParameterizedTest(name = "{displayName} : {arguments}")
    @CsvSource({"10000,30000,HP,Lenovo,13", "9999,15000,HP,Lenovo,900", "10001,29999,HP,Lenovo,6"})
    public void testLaptopsPageYandex(String startPrice, String endPrice, String firstProduct, String secondProduct,
                                   String resultOfElements) {
        chromeDriver.get(configProperties.baseUrl());
        PageFactoryMainMarket pageFactoryMainMarket = PageFactory.initElements(chromeDriver,PageFactoryMainMarket.class);
        pageFactoryMainMarket.find();
        PageFactoryLaptopsMarket pageFactoryLaptopsMarket = PageFactory.initElements(chromeDriver,PageFactoryLaptopsMarket.class);
        pageFactoryLaptopsMarket.setParameters(startPrice, endPrice, firstProduct, secondProduct);
        Assertions.assertTrue(pageFactoryLaptopsMarket.getNumbersOfElements(), "Title is not correct " + resultOfElements + " - is your title");

    }

    @Feature("Проверка соответствия элементов заданным параметрам")
    @DisplayName("Проверка результатов ввода и вывода")
    @ParameterizedTest(name = "{displayName} : {arguments}")
    @MethodSource({"helpers.DataProvider#providerCheckingElementsList"})
    public void testLaptopsAfterSearchPageYandex(String title) {
        openSite("https://market.yandex.ru/","Ноутбуки",chromeDriver);
        checkHeaderLaptops(title);
        checkNumbersOfElements();
        checkAllElementsEqualsChoice();

        /*PageFactoryLaptopsAfterSearch pageFactoryLaptopsAfterSearch = PageFactory.initElements(chromeDriver,PageFactoryLaptopsAfterSearch.class);
        //pageFactoryLaptopsAfterSearch.comparingElementsWithInputParameters(results);
        Assertions.assertTrue(results.contains(configProperties.products()), "Бренд указан не верно: " + results + " - указан бренд");*/
    }




}
