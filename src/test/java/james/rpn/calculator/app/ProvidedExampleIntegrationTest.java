package james.rpn.calculator.app;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.google.inject.Guice;
import com.google.inject.Injector;

import james.rpn.calculator.element.operator.api.InsufficientParameterException;
import james.rpn.calculator.statemachine.api.RpnStateMachine;
import james.rpn.calculator.statemachine.internal.RpnStateMachineModule;

public class ProvidedExampleIntegrationTest {
    private RpnStateMachine rpnStateMachine;

    @BeforeEach
    protected void setup() {
        final Injector injector = Guice.createInjector(new RpnStateMachineModule());

        rpnStateMachine = injector.getInstance(RpnStateMachine.class);
    }

    @Test
    protected void test_example_1() {
        // Arrange + Act
        rpnStateMachine.apply("5 2");

        // Assert
        assertStackSizeAndContent(2, "5 2");
    }

    @Test
    protected void test_example_2() {
        // Arrange + Act
        rpnStateMachine.apply("2 sqrt");

        // Assert
        assertStackSizeAndContent(1, "1.4142135623");

        // Arrange + Act
        rpnStateMachine.apply("clear 9 sqrt");

        // Assert
        assertStackSizeAndContent(1, "3");
    }

    @Test
    protected void test_example_3() {
        // Arrange + Act
        rpnStateMachine.apply("5 2 -");

        // Assert
        assertStackSizeAndContent(1, "3");

        // Arrange + Act
        rpnStateMachine.apply("3 -");

        // Assert
        assertStackSizeAndContent(1, "0");

        // Arrange + Act
        rpnStateMachine.apply("clear");

        // Assert
        assertStackSizeAndContent(0, "");
    }

    @Test
    protected void test_example_4() {

        // Arrange + Act
        rpnStateMachine.apply("5 4 3 2");

        // Assert
        assertStackSizeAndContent(4, "5 4 3 2");

        // Arrange + Act
        rpnStateMachine.apply("undo undo *");

        // Assert
        assertStackSizeAndContent(1, "20");

        // Arrange + Act
        rpnStateMachine.apply("5 *");

        // Assert
        assertStackSizeAndContent(1, "100");

        // Arrange + Act
        rpnStateMachine.apply("undo");

        // Assert
        assertStackSizeAndContent(2, "20 5");
    }

    @Test
    protected void test_example_5() {
        // Arrange + Act
        rpnStateMachine.apply("7 12 2 /");

        // Assert
        assertStackSizeAndContent(2, "7 6");

        // Arrange + Act
        rpnStateMachine.apply("*");

        // Assert
        assertStackSizeAndContent(1, "42");

        // Arrange + Act
        rpnStateMachine.apply("4 /");

        // Assert
        assertStackSizeAndContent(1, "10.5");
    }

    @Test
    protected void test_example_6() {
        // Arrange + Act
        rpnStateMachine.apply("1 2 3 4 5");

        // Assert
        assertStackSizeAndContent(5, "1 2 3 4 5");

        // Arrange + Act
        rpnStateMachine.apply("*");

        // Assert
        assertStackSizeAndContent(4, "1 2 3 20");

        // Arrange + Act
        rpnStateMachine.apply("clear 3 4 -");

        // Assert
        assertStackSizeAndContent(1, "-1");
    }

    @Test
    protected void test_example_7() {
        // Arrange + Act
        rpnStateMachine.apply("1 2 3 4 5");

        // Assert
        assertStackSizeAndContent(5, "1 2 3 4 5");

        // Arrange + Act
        rpnStateMachine.apply("* * * *");

        // Assert
        assertStackSizeAndContent(1, "120");
    }

    @Test
    protected void test_example_8() {
        // Arrange + Act + Assert
        final InsufficientParameterException exception = Assertions.assertThrows(InsufficientParameterException.class, () ->
            rpnStateMachine.apply("1 2 3 * 5 + * * 6 5"));

        Assertions.assertEquals(15, exception.getOperatorPosition());
        Assertions.assertEquals("*", exception.getOperatorName());
    }

    private void assertStackSizeAndContent(final int size, final String stackPrint) {
        Assertions.assertEquals(size, rpnStateMachine.getCurrentState().getState().getStack().size());
        Assertions.assertEquals(stackPrint, rpnStateMachine.getCurrentState().getState().toString());
    }
}
