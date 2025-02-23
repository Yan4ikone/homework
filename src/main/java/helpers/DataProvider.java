package helpers;

import net.bytebuddy.asm.Advice;
import org.junit.jupiter.params.provider.Arguments;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class DataProvider {

    public static Stream<Arguments> providerCheckingElements() {
        return Stream.of(
                Arguments.of("HP", "Lenovo")
        );
    }

    public static Stream<Arguments> providerCheckingElementsList() {
        List<String>elements = new ArrayList<>();
        elements.add("HP");
        elements.add("Lenovo");
        return Stream.of(
                Arguments.of(elements)
        );

    }
}

