package james.rpn.calculator.app;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.google.inject.Guice;
import com.google.inject.Injector;

import james.rpn.calculator.element.operator.api.InsufficientParameterException;
import james.rpn.calculator.element.operator.api.RpnOperator;
import james.rpn.calculator.element.operator.api.RpnOperatorFactory;
import james.rpn.calculator.state.api.RpnState;
import james.rpn.calculator.state.internal.RpnStateModule;

public class ProvidedExampleIntegrationTest {
    private RpnState state;
    private RpnOperatorFactory rpnOperatorFactory;

    @BeforeEach
    protected void setup() {
        final Injector injector = Guice.createInjector(new RpnStateModule());

        state = injector.getInstance(RpnState.class);
        rpnOperatorFactory = injector.getInstance(RpnOperatorFactory.class);
    }

    @Test
    protected void test_example_1() {
        // Arrange + Act
        stateTransit("5", "2");

        // Assert
        Assertions.assertEquals(2, state.getState().getStack().size());
        Assertions.assertEquals("[5, 2]", state.getState().getStack().toString());
    }

    @Test
    protected void test_example_2() {
        // Arrange + Act
        stateTransit("2", "sqrt");

        // Assert
        Assertions.assertEquals(1, state.getState().getStack().size());
        Assertions.assertEquals("[1.4142135623]", state.getState().getStack().toString());

        // Arrange + Act
        stateTransit("clear", "9", "sqrt");

        // Assert
        Assertions.assertEquals(1, state.getState().getStack().size());
        Assertions.assertEquals("[3]", state.getState().getStack().toString());
    }

    @Test
    protected void test_example_3() {
        // Arrange + Act
        stateTransit("5", "2", "-");

        // Assert
        Assertions.assertEquals(1, state.getState().getStack().size());
        Assertions.assertEquals("[3]", state.getState().getStack().toString());

        // Arrange + Act
        stateTransit("3", "-");

        // Assert
        Assertions.assertEquals(1, state.getState().getStack().size());
        Assertions.assertEquals("[0]", state.getState().getStack().toString());

        // Arrange + Act
        stateTransit("clear");

        // Assert
        Assertions.assertEquals(0, state.getState().getStack().size());
        Assertions.assertEquals("[]", state.getState().getStack().toString());
    }

    @Test
    protected void test_example_4() {

        // Arrange + Act
        stateTransit("5", "4", "3", "2");

        // Assert
        Assertions.assertEquals(4, state.getState().getStack().size());
        Assertions.assertEquals("[5, 4, 3, 2]", state.getState().getStack().toString());

        // Arrange + Act
        stateTransit("undo", "undo", "*");

        // Assert
        Assertions.assertEquals(1, state.getState().getStack().size());
        Assertions.assertEquals("[20]", state.getState().getStack().toString());

        // Arrange + Act
        stateTransit("5", "*");

        // Assert
        Assertions.assertEquals(1, state.getState().getStack().size());
        Assertions.assertEquals("[100]", state.getState().getStack().toString());

        // Arrange + Act
        stateTransit("undo");

        // Assert
        Assertions.assertEquals(2, state.getState().getStack().size());
        Assertions.assertEquals("[20, 5]", state.getState().getStack().toString());
    }

    @Test
    protected void test_example_5() {
        // Arrange + Act
        stateTransit("7", "12", "2", "/");

        // Assert
        Assertions.assertEquals(2, state.getState().getStack().size());
        Assertions.assertEquals("[7, 6]", state.getState().getStack().toString());

        // Arrange + Act
        stateTransit("*");

        // Assert
        Assertions.assertEquals(1, state.getState().getStack().size());
        Assertions.assertEquals("[42]", state.getState().getStack().toString());

        // Arrange + Act
        stateTransit("4", "/");

        // Assert
        Assertions.assertEquals(1, state.getState().getStack().size());
        Assertions.assertEquals("[10.5]", state.getState().getStack().toString());
    }

    @Test
    protected void test_example_6() {
        // Arrange + Act
        stateTransit("1", "2", "3", "4", "5");

        // Assert
        Assertions.assertEquals(5, state.getState().getStack().size());
        Assertions.assertEquals("[1, 2, 3, 4, 5]", state.getState().getStack().toString());

        // Arrange + Act
        stateTransit("*");

        // Assert
        Assertions.assertEquals(4, state.getState().getStack().size());
        Assertions.assertEquals("[1, 2, 3, 20]", state.getState().getStack().toString());

        // Arrange + Act
        stateTransit("clear", "3", "4", "-");

        // Assert
        Assertions.assertEquals(1, state.getState().getStack().size());
        Assertions.assertEquals("[-1]", state.getState().getStack().toString());
    }

    @Test
    protected void test_example_7() {
        // Arrange + Act
        stateTransit("1", "2", "3", "4", "5");

        // Assert
        Assertions.assertEquals(5, state.getState().getStack().size());
        Assertions.assertEquals("[1, 2, 3, 4, 5]", state.getState().getStack().toString());

        // Arrange + Act
        stateTransit("*", "*", "*", "*");

        // Assert
        Assertions.assertEquals(1, state.getState().getStack().size());
        Assertions.assertEquals("[120]", state.getState().getStack().toString());
    }

    @Test
    protected void test_example_8() {
        // Arrange + Act + Assert
        Assertions.assertThrows(InsufficientParameterException.class, () ->
            stateTransit("1", "2", "3", "*", "5", "+", "*", "*", "6", "5"));
    }

    /**
     * state transition operations with input.
     * @param input
     */
    private void stateTransit(final String... input) {
        final List<RpnOperator> operators =
            Arrays.asList(input)
                .stream()
                .map(rpnOperatorFactory::getRpnOperatorFromString)
                .collect(Collectors.toList());

        state.transit(operators);
    }
}
