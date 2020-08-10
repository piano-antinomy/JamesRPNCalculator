package james.rpn.calculator.element.operator.internal;

import java.math.BigDecimal;
import java.util.Stack;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import james.rpn.calculator.element.operator.api.RpnNumber;
import james.rpn.calculator.element.operator.api.RpnOperator;
import james.rpn.calculator.stack.api.RpnStack;

public class UndoOperatorTest {

    private final UndoOperator target = new UndoOperator();

    @Test
    protected void test_operation() {

        // Arrange
        final RpnStack previous = Mockito.mock(RpnStack.class);
        final Stack<RpnOperator> stack = new Stack<>();
        stack.push(new RpnNumber(new BigDecimal("1")));

        final RpnStack initialStack = new RpnStack(new RpnNumber(new BigDecimal("2")), previous, stack);

        // Act
        final RpnStack result = target.act(initialStack);

        // Assert
        Assertions.assertEquals(previous, result);
    }

}
