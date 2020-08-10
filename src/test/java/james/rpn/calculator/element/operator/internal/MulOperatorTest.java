package james.rpn.calculator.element.operator.internal;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class MulOperatorTest extends AbstractTwoParametersOperatorTest {

    public MulOperatorTest() {
        super(new MulOperator());
    }

    @ParameterizedTest
    @CsvSource({"3, 4, 12", "1, 2, 2", "10.5, 12.77, 134.085"})
    protected void test_MulOperations(final String left, final String right, final String resultNumber) {
        test_Operation(left, right, resultNumber);
    }
}
