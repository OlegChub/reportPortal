package testData;

import org.junit.jupiter.params.provider.Arguments;

import java.util.stream.Stream;

public class DataProvider {
    static Stream<Arguments> dataForLaunchExecutionsTest() {
        return Stream.of(
                Arguments.arguments("total", 20),
                Arguments.arguments("failed", 8),
                Arguments.arguments("passed", 10),
                Arguments.arguments("skipped", 2)
        );
    }
}
