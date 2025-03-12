package helpers;

import org.junit.jupiter.params.provider.Arguments;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;
/**
 * Класс дата провайдера, для передачи данных.
 * @author Yan
 */
public class DataProvider {

    public static Stream<Arguments> providerCheckingElements() {
        List<String>elements = new ArrayList<>();
        elements.add("HP");
        elements.add("Lenovo");
        return Stream.of(
                Arguments.of("Ноутбуки","10000","50000","HP", "Lenovo", 12, elements)
        );
    }
}

