package steps;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import pages.PageFactoryLaptopsMarket;
import pages.PageFactoryMainMarket;

import static helpers.Properties.configProperties;
import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfAllElements;

public class StepsAll {

    private static WebDriver wait;
    private static WebDriver driver;


    @Step("Проверка перехода на страницу Ноутбуки {title}")
    public static void checkHeaderLaptops(PageFactoryMainMarket pageFactoryMainMarket, String title) {
        pageFactoryMainMarket.find();
        pageFactoryMainMarket.getHeaderAfterClick(title);
    }

    @Step("Проверка поиска количества элементов больше 12 {title}")
    public static boolean checkNumbersOfElements(PageFactoryLaptopsMarket pageFactoryLaptopsMarket) {
        configProperties.timeOut();
        pageFactoryLaptopsMarket.getNumbersOfElements();

        return false;
    }


}


















