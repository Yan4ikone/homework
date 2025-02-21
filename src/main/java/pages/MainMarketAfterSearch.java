package pages;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.beans.Visibility;
import java.util.Set;

import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated;

public class MainMarketAfterSearch {

    private WebDriver driver;
    private WebDriverWait wait;
    private String getTitle;

    public WebDriver getWebDriver() {
        return driver;
    }

    public MainMarketAfterSearch(WebDriver webDriver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, 10);
        driver.get("https://market.yandex.ru/");
    }

    public void checkingTitleAfterClickLaptops () {
        //wait.until(driver.getCurrentUrl().contains("")); // Ожидание загрузки тайтла
        Assertions.assertFalse(driver.getCurrentUrl().contains("catalog--noutbuki")); // Исправить т.к. фолс ассерт
    }

    public void goPageByLinkName(String link) {
        //wait.until()
        //driver.findElement()

        Set<String> tabs =driver.getWindowHandles();
        for (String tab:tabs)
            driver.switchTo().window(tab);
    }

    public String getTitle() {
        getTitle = driver.getTitle();
        return getTitle;
    }
}