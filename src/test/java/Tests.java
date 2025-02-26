import io.qameta.allure.Feature;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import static helpers.Properties.configProperties;
import static steps.StepsAll.*;

public class Tests extends BaseTest {

    @Feature("Полная проверка соответствия элементов заданным параметрам")
    @DisplayName("Проверка результатов теста разбитая на Step")
    @ParameterizedTest(name = "{displayName} : {arguments}")
    @MethodSource({"helpers.DataProvider#providerCheckingElementsList"})
    public void testLaptopsAfterSearchPageYandex() {
        openSite(configProperties.baseUrl(), chromeDriver);
        checkHeaderLaptops("Ноутбуки", chromeDriver);
        setFinderParameters(configProperties.startPrice(),
                configProperties.endPrice(),
                configProperties.firstProduct(),
                configProperties.secondProduct(),
                chromeDriver);
        checkNumbersOfElements(chromeDriver);
        checkAllElementsEqualsChoice(chromeDriver);
        returnFirstElementToSearch(chromeDriver);
    }
}
