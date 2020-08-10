package james.rpn.calculator.state.internal;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import james.rpn.calculator.element.operator.api.RpnOperator;
import james.rpn.calculator.stack.api.RpnStack;
import james.rpn.calculator.state.api.RpnState;

public class RpnStateImplTest {

    private final RpnState target = new RpnStateImpl();

    @Test
    protected void test_initialStackIsEmpty() {
        // Assert
        Assertions.assertTrue(target.getState().getStack().isEmpty());
    }

    @Test
    protected void test_transitAfterApplyingOperations() {
        // Arrange
        final RpnOperator operator = Mockito.mock(RpnOperator.class);
        final RpnStack resultStack = Mockito.mock(RpnStack.class);
        Mockito.when(operator.act(Mockito.eq(target.getState()))).thenReturn(resultStack);

        // Act
        target.transit(operator);

        // Assert
        Assertions.assertEquals(resultStack, target.getState());
    }
}
