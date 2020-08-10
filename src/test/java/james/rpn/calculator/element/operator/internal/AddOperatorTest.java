package james.rpn.calculator.element.operator.internal;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class AddOperatorTest extends AbstractTwoParametersOperatorTest {

    AddOperatorTest() {
        super(new AddOperator());
    }

    @ParameterizedTest
    @CsvSource({"3, 4, 7", "1, 2, 3", "10.5, 12.77, 23.27"})
    protected void test_AddOperations(final String left, final String right, final String resultNumber) {
        test_Operation(left, right, resultNumber);
    }
}
