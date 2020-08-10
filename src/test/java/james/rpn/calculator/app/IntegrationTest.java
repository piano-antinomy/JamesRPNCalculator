package james.rpn.calculator.app;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.google.inject.Guice;
import com.google.inject.Injector;

import james.rpn.calculator.element.operator.api.InsufficientParameterException;
import james.rpn.calculator.statemachine.api.RpnStateMachine;
import james.rpn.calculator.statemachine.internal.RpnStateMachineModule;

public class IntegrationTest {
    private RpnStateMachine rpnStateMachine;

    @BeforeEach
    protected void setup() {
        final Injector injector = Guice.createInjector(new RpnStateMachineModule());

        rpnStateMachine = injector.getInstance(RpnStateMachine.class);
    }


    @Test
    protected void testAddOperation() {

        // Arrange + Act
        rpnStateMachine.apply("1 2 + 15.5 -9.8 +");

        // Assert
        assertStackSizeAndContent(2, "3 5.7");
    }

    @Test
    protected void testSubOperation() {

        // Arrange + Act
        rpnStateMachine.apply("5 3 - 15.5 -9.8 +");

        // Assert
        assertStackSizeAndContent(2, "2 5.7");
    }

    @Test
    protected void testMulOperation() {

        // Arrange + Act
        rpnStateMachine.apply("5 3 - 15.5 -9.8 *");

        // Assert
        assertStackSizeAndContent(2, "2 -151.9");
    }

    @Test
    protected void testDivOperation() {

        // Arrange + Act
        rpnStateMachine.apply("5 3 - 15.5 202 /");

        // Assert
        assertStackSizeAndContent(2, "2 0.0767326732");
    }

    @Test
    protected void testUndoOperation() {

        // Arrange + Act
        rpnStateMachine.apply("5 3 - 15.5 202");

        // Assert
        assertStackSizeAndContent(3, "2 15.5 202");

        // Act 2
        rpnStateMachine.apply("/");

        // Assert 2
        assertStackSizeAndContent(2, "2 0.0767326732");

        // Act 3
        rpnStateMachine.apply("undo");

        // Assert 3
        assertStackSizeAndContent(3, "2 15.5 202");
    }

    @Test
    protected void testNoOperation() {

        // Arrange + Act
        rpnStateMachine.apply("1 2 14 15.5 19.8 0");

        // Assert
        assertStackSizeAndContent(6, "1 2 14 15.5 19.8 0");
    }

    @Test
    protected void testClearOperation() {

        // Arrange + Act
        rpnStateMachine.apply("1 2 14 15.5 19.8 0 clear");

        // Assert
        assertStackSizeAndContent(0, "");
    }

    @Test
    protected void testClearOperationBeforeOthers() {

        // Arrange + Act
        rpnStateMachine.apply("1 2 + 15.5 19.8 0 clear 20.123456789012 21 +");

        // Assert
        assertStackSizeAndContent(1, "41.123456789");

        // Act
        rpnStateMachine.apply("23 + clear undo 6 /");

        // Assert
        assertStackSizeAndContent(1, "10.6872427981");
    }

    @Test
    protected void testInsufficientParams() {
        // Arrange
        final String input = "2 +";

        // Act + Assert
        Assertions.assertThrows(InsufficientParameterException.class, () -> rpnStateMachine.apply(input));
    }

    private void assertStackSizeAndContent(final int size, final String stackPrint) {
        Assertions.assertEquals(size, rpnStateMachine.getCurrentState().getState().getStack().size());
        Assertions.assertEquals(stackPrint, rpnStateMachine.getCurrentState().getState().toString());
    }
}
