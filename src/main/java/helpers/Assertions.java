package helpers;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

import static helpers.Properties.configProperties;

public class Assertions {

    private WebDriver driver;
    private WebDriverWait wait;
    private JavascriptExecutor scroll;
    private long lastHeight;

    @FindBy(how = How.XPATH, using = "//h1[contains(text(), 'Ноутбуки')]")
    WebElement headerAfterClick;

    @FindBy(how = How.XPATH, using = "//span[@data-auto='snippet-title']")
    List<WebElement> productList;

    public Assertions(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, configProperties.timeOut());
        this.scroll = (JavascriptExecutor) driver;
        PageFactory.initElements(driver, this);
    }

    /**
     * Проверка заголовка после перехода в раздел "Ноутбуки".
     * @param title
     */
    public void checkTitleByLink(String title) {
        wait.until(ExpectedConditions.visibilityOfAllElements(headerAfterClick));
        org.junit.jupiter.api.Assertions.assertTrue(driver.getTitle().contains(title), "Ошибка, заголовок не найден, Ваш заголовок: " + title);
    }

    //Проверка корректного отображения элементов на странице
    public void getNumbersOfElements(int findElement) {
        wait.until(ExpectedConditions.visibilityOfAllElements(productList));
        org.junit.jupiter.api.Assertions.assertTrue(productList.size() > findElement);
    }

    public void comparingElementsWithInputParameters(List<String> elements) {
        while (true) {
            scroll.executeScript("window.scrollTo(0,document.body.scrollHeight);");
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='/marketfrontSerpLayout']")));
            long newHeight = (long) scroll.executeScript("return document.body.scrollHeight;");
            if (newHeight == lastHeight) {
                break;
            }
            lastHeight = newHeight;
            List<WebElement> productList1 = driver.findElements(By.xpath("//span[@data-auto='snippet-title']"));
            org.junit.jupiter.api.Assertions.assertTrue(productList1.stream()
                    .map(WebElement::getText)
                    .allMatch(title -> elements.stream().anyMatch(title::contains)));

        }
    }
}
