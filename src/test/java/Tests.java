import io.qameta.allure.Feature;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;

import static helpers.Properties.configProperties;
import static steps.StepsAll.*;

/**
 * Тестовый класс.
 * @author Yan
 */

public class Tests extends BaseTest {
/** Тестовый класс является наследником BaseTest.
* @param title - заголовок
* @param startPrice - цена "от"
* @param endPrice - цена "до"
* @param firstProduct - первый производитель
* @param secondProduct - второй производитель
* @param findElement - количество сравниваемых элементов
* @param elements - наименование фирм-производителей
* Класс разбит на шаги {@link steps.StepsAll}
*/
    @Feature("Полная проверка соответствия элементов заданным параметрам")
    @DisplayName("Проверка результатов теста разбитая на Step")
    @ParameterizedTest(name = "{displayName}: {arguments}")
    @MethodSource("helpers.DataProvider#providerCheckingElements")
    public void testLaptopsAfterSearchPageYandex(String title, String startPrice, String endPrice,
                                                 String firstProduct, String secondProduct,
                                                 int findElement, List<String> elements) {
        openSite(configProperties.baseUrl(), chromeDriver);
        checkHeaderLaptops(title);
        setFinderParameters(startPrice, endPrice, firstProduct, secondProduct);
        checkNumbersOfElements(findElement);
        checkAllElementsEqualsChoice(elements);
        returnFirstElementToSearch();
    }
}
