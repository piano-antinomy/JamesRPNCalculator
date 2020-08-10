package james.rpn.calculator.element.operator.internal;

import java.math.BigDecimal;
import java.util.Stack;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import james.rpn.calculator.element.operator.api.InsufficientParameterException;
import james.rpn.calculator.element.operator.api.RpnNumber;
import james.rpn.calculator.element.operator.api.RpnOperator;
import james.rpn.calculator.stack.api.RpnStack;

public abstract class AbstractTwoParametersOperatorTest {

    final RpnOperator target;
    final String[] opertaionNumbers;

    AbstractTwoParametersOperatorTest(final RpnOperator target, final String... opertaionNumbers) {
        this.target = target;
        this.opertaionNumbers = opertaionNumbers;
    }

    @Test
    protected void test_operation() {

        // Arrange
        final Stack<RpnOperator> stack = new Stack<>();
        stack.push(new RpnNumber(new BigDecimal(opertaionNumbers[0])));
        stack.push(new RpnNumber(new BigDecimal(opertaionNumbers[1])));

        final RpnStack initialStack = new RpnStack(new RpnNumber(new BigDecimal("2")), null, stack);

        // Act
        final RpnStack result = target.act(initialStack);

        // Assert
        Assertions.assertEquals(opertaionNumbers[2], result.toString());
    }

    @Test
    protected void test_InsufficientParameters() {
        // Arrange
        final Stack<RpnOperator> stack = new Stack<>();
        stack.push(new RpnNumber(new BigDecimal("1")));

        final RpnStack initialStack = new RpnStack(new RpnNumber(new BigDecimal("1")), null, stack);

        // Act + Assert
        Assertions.assertThrows(InsufficientParameterException.class, () -> target.act(initialStack));
    }

}
