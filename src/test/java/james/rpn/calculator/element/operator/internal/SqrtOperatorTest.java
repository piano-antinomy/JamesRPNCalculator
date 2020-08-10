package james.rpn.calculator.element.operator.internal;

import java.math.BigDecimal;
import java.util.Stack;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import james.rpn.calculator.element.operator.api.InsufficientParameterException;
import james.rpn.calculator.element.operator.api.RpnNumber;
import james.rpn.calculator.element.operator.api.RpnOperator;
import james.rpn.calculator.stack.api.RpnStack;

public class SqrtOperatorTest {
    private final SqrtOperator target = new SqrtOperator();

    @Test
    protected void test_operation() {

        // Arrange
        final Stack<RpnOperator> stack = new Stack<>();
        stack.push(new RpnNumber(new BigDecimal("9")));

        final RpnStack initialStack = new RpnStack(new RpnNumber(new BigDecimal("9")), null, stack);

        // Act
        final RpnStack result = target.act(initialStack);

        // Assert
        Assertions.assertEquals("3", result.toString());
    }

    @Test
    protected void test_InsufficientParameters() {
        // Arrange
        final Stack<RpnOperator> stack = new Stack<>();

        final RpnStack initialStack = new RpnStack(null, null, stack);

        // Act + Assert
        Assertions.assertThrows(InsufficientParameterException.class, () -> target.act(initialStack));
    }


}
