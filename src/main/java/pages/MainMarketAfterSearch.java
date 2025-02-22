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
        this.wait = new WebDriverWait(driver, 7);
        driver.get("https://market.yandex.ru/");
        wait.until(visibilityOfElementLocated(By.xpath("//div[@data-baobab-name='login_popup']")));

    }

    public void checkingTitleAfterClickLaptops () {
        wait.until(visibilityOfElementLocated(By.xpath("//a[@href='/catalog--noutbuki/26895412/list?hid=91013']")));

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