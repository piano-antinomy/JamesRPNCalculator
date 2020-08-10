package james.rpn.calculator.element.operator.internal;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class SubOperatorTest extends AbstractTwoParametersOperatorTest {

    public SubOperatorTest() {
        super(new SubOperator());
    }

    @ParameterizedTest
    @CsvSource({"3, 4, -1", "1, 2, -1", "10.5, 12.77, -2.27"})
    protected void test_MulOperations(final String left, final String right, final String resultNumber) {
        test_Operation(left, right, resultNumber);
    }
}
