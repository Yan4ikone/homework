import io.qameta.allure.Feature;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import static helpers.Properties.configProperties;
import static steps.StepsAll.*;

public class Tests extends BaseTest {

    @Feature("Полная проверка соответствия элементов заданным параметрам")
    @DisplayName("Проверка результатов теста разбитая на Step")
    @ParameterizedTest(name = "{displayName}: {arguments}")
    @MethodSource("helpers.DataProvider#providerCheckingElements")
    public void testLaptopsAfterSearchPageYandex(String title,String startPrice, String endPrice,
                                                 String firstProduct, String secondProduct, String findElement) {
        openSite(configProperties.baseUrl(), chromeDriver);
        checkHeaderLaptops(title);
        setFinderParameters(startPrice, endPrice, firstProduct, secondProduct);
        checkNumbersOfElements(findElement);
        checkAllElementsEqualsChoice();
        returnFirstElementToSearch();
    }
}
