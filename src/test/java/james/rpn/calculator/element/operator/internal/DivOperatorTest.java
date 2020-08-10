package james.rpn.calculator.element.operator.internal;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class DivOperatorTest extends AbstractTwoParametersOperatorTest {

    public DivOperatorTest() {
        super(new DivOperator());
    }

    @ParameterizedTest
    @CsvSource({"3, 4, 0.75", "1, 2, 0.5", "10.5, 12.77, 0.8222396241"})
    protected void test_DivOperations(final String left, final String right, final String resultNumber) {
        test_Operation(left, right, resultNumber);
    }
}
