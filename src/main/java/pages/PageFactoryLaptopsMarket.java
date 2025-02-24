package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

import static helpers.Properties.configProperties;
import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfAllElements;

public class PageFactoryLaptopsMarket {

    private WebDriver driver;
    private WebDriverWait wait;
    private Actions actions;

    @FindBy(how = How.XPATH, using = "//*[@id='range-filter-field-glprice_25563_min']")
    WebElement buttonStartPrice;

    @FindBy(how = How.XPATH, using = "//*[@id='range-filter-field-glprice_25563_max']")
    WebElement buttonEndPrice;

    @FindBy(how = How.XPATH, using = "//span[contains(text(), 'Показать всё')]")
    WebElement buttonShowAll;

    @FindBy(how = How.XPATH, using = "//*[@placeholder='Найти']")
    WebElement searchField;

    @FindBy(how = How.XPATH, using = "//span[text()='HP']")
    WebElement buttonHewlett;

    @FindBy(how = How.XPATH, using = "//span[text()='Lenovo']")
    WebElement buttonLenovo;

    @FindBy(how = How.XPATH, using = "//span[@data-auto='snippet-title']")
    List<WebElement> productList;

    @FindBy(how = How.XPATH, using = "//h3[@data-zone-name='title']")
    List<WebElement> productTitles;

    @FindBy(how = How.XPATH, using = "//*[@id='/marketfrontSerpLayout']")
    List<WebElement> productChoice;



    public PageFactoryLaptopsMarket(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, configProperties.timeOut());
        this.actions = new Actions(driver);
        PageFactory.initElements(driver, this);
    }

    public void setParameters(String startPrice, String endPrice,
                              String firstProduct, String secondProduct) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='range-filter-field-glprice_25563_min']")));
        buttonStartPrice.click();
        buttonStartPrice.sendKeys(startPrice);
        buttonEndPrice.click();
        buttonEndPrice.sendKeys(endPrice);
        JavascriptExecutor scroll = (JavascriptExecutor)driver;
        scroll.executeScript("window.scrollBy(0,350)");
        wait.until(ExpectedConditions.visibilityOfAllElements(productChoice));
        buttonShowAll.click();
        wait.until(ExpectedConditions.visibilityOfAllElements(searchField));
        searchField.click();
        wait.until(ExpectedConditions.visibilityOfAllElements(searchField));
        searchField.sendKeys(firstProduct);
        wait.until(ExpectedConditions.visibilityOfAllElements(productChoice));
        buttonHewlett.click();
        wait.until(ExpectedConditions.visibilityOfAllElements(productChoice));
        searchField.clear();
        searchField.sendKeys(secondProduct);
        wait.until(ExpectedConditions.visibilityOfAllElements(buttonLenovo));
        buttonLenovo.click();
    }

    public boolean getNumbersOfElements() {
        wait.until(visibilityOfAllElements(productList));
        return productList.size() > 12;
    }
}



