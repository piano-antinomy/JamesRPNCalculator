package james.rpn.calculator.statemachine.internal;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import james.rpn.calculator.element.operator.api.InsufficientParameterException;
import james.rpn.calculator.element.operator.api.RpnOperator;
import james.rpn.calculator.element.operator.api.RpnOperatorFactory;
import james.rpn.calculator.state.api.RpnState;
import james.rpn.calculator.statemachine.api.RpnStateMachine;

public class RpnStateMachineImplTest {

    final RpnState state = Mockito.mock(RpnState.class);
    final RpnOperatorFactory rpnOperatorFactory = Mockito.mock(RpnOperatorFactory.class);
    final RpnOperator rpnOperator = Mockito.mock(RpnOperator.class);
    final RpnStateMachine target = new RpnStateMachineImpl(state, rpnOperatorFactory);

    @BeforeEach
    public void setup() {

        Mockito.when(rpnOperatorFactory.getRpnOperatorFromString(Mockito.anyString())).thenReturn(rpnOperator);
        Mockito.doNothing().when(state).transit(rpnOperator);
    }

    @Test
    protected void test_inputString_parsedAsExpectedOperators() {
        // Arrange
        final String inputString = "1 2 3 4 *     * clear 5 4  3 sqrt * undo 10 /  ";

        // Act
        target.apply(inputString);

        // Assert
        Mockito.verify(state, Mockito.times(15)).transit(rpnOperator);
    }

    @Test
    protected void test_inputString_ExceptionReflectsThePosition() {
        // Arrange
        final String inputString = "1 2 3 4 * - * clear 5 4  3 sqrt * undo 10 /  ";
        final RpnOperator rpnOperatorWithInsufficientArgs = Mockito.mock(RpnOperator.class);
        Mockito.doThrow(new InsufficientParameterException("-")).when(state).transit(rpnOperatorWithInsufficientArgs);
        Mockito.when(rpnOperatorFactory.getRpnOperatorFromString("-")).thenReturn(rpnOperatorWithInsufficientArgs);


        // Act
        final InsufficientParameterException exception = Assertions.assertThrows(InsufficientParameterException.class, () -> target.apply(inputString));
        Assertions.assertEquals("-", exception.getOperatorName());
        Assertions.assertEquals(11, exception.getOperatorPosition());
    }
}
