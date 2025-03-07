package helpers;

import org.junit.jupiter.params.provider.Arguments;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class DataProvider {

    public static Stream<Arguments> providerCheckingElements() {
        return Stream.of(
                Arguments.of("Ноутбуки","10000","30000","HP", "Lenovo","12")
        );
    }

    public static Stream<Arguments> providerCheckingElementsList() {
        List<String>elements = new ArrayList<>();
        elements.add("10000");
        elements.add("30000");
        elements.add("Ноутбуки");
        elements.add("HP");
        elements.add("Lenovo");
        elements.add("12");
        return Stream.of(
                Arguments.of(elements)
        );

    }
}

