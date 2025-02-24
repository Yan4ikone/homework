package pages;

import helpers.DataProvider;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Collections;
import java.util.List;

import static helpers.Properties.configProperties;
import static org.openqa.selenium.support.ui.ExpectedConditions.textMatches;
import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfAllElements;

public class PageFactoryLaptopsAfterSearch {

    private WebDriver driver;
    private WebDriverWait wait;
    private long lastHeight;
    private JavascriptExecutor scroll;

    @FindBy(how = How.XPATH, using = "//span[@data-auto='snippet-title']")
    List<WebElement> productList;

public PageFactoryLaptopsAfterSearch(WebDriver driver) {
    this.driver = driver;
    this.scroll = (JavascriptExecutor)driver;
    this.lastHeight = (long) scroll.executeScript("return document.body.scrollHeight");
    this.wait = new WebDriverWait(driver, configProperties.timeOut());
    PageFactory.initElements(driver, this);
}

public boolean comparingElementsWithInputParameters() {
    while (true) {
        scroll.executeScript("window.scrollTo(0,document.body.scrollHeight);");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h3[@data-zone-name='title']")));
        long newHeight = (long) scroll.executeScript("return document.body.scrollHeight);");
        if (newHeight == lastHeight) {
            break;
        }
        lastHeight = newHeight;
    }
    return productList.stream()
            .map(WebElement::getText)
            .allMatch(title -> configProperties.products().stream().anyMatch(title::contains));
    }
}


