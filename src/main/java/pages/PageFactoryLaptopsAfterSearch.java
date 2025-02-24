package pages;

import helpers.DataProvider;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Collections;
import java.util.List;

import static helpers.Properties.configProperties;
import static org.openqa.selenium.support.ui.ExpectedConditions.textMatches;
import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfAllElements;

public class PageFactoryLaptopsAfterSearch {

    private WebDriver driver;
    private WebDriverWait wait;

    @FindBy(how = How.XPATH, using = "//span[@data-auto='snippet-title']")
    List<WebElement> productList;

public PageFactoryLaptopsAfterSearch(WebDriver driver) {
    this.driver = driver;
}

public boolean comparingElementsWithInputParameters() {
    driver.get(configProperties.laptopUrl());
    wait.until(visibilityOfAllElements(productList));
    JavascriptExecutor scroll = (JavascriptExecutor)driver;
    scroll.executeScript("window.scrollBy(0,250)");
    wait.until(visibilityOfAllElements(productList));
    List<WebElement>webElements = productList;
    for (WebElement element : webElements) {
        String text = element.getText();
        boolean containsAtLeastOne = configProperties.products().stream().anyMatch(text::contains);
        if (!containsAtLeastOne) {
            return false;
        }

    }
    return true;



    }
}


