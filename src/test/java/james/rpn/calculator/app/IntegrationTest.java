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

public class IntegrationTest {
    private RpnState state;
    private RpnOperatorFactory rpnOperatorFactory;

    @BeforeEach
    protected void setup() {
        final Injector injector = Guice.createInjector(new RpnStateModule());

        state = injector.getInstance(RpnState.class);
        rpnOperatorFactory = injector.getInstance(RpnOperatorFactory.class);
    }


    @Test
    protected void testAddOperation() {

        // Arrange
        final String[] input = {"1", "2", "+", "15.5", "-9.8", "+"};

        // Act
        stateTransit(input);

        // Assert
        Assertions.assertEquals(2, state.getState().size());
        Assertions.assertEquals("[3, 5.7]", state.getState().toString());
    }

    @Test
    protected void testNoOperation() {

        // Arrange
        final String[] input = {"1", "2", "14", "15.5", "19.8", "0"};

        // Act
        stateTransit(input);

        // Assert
        Assertions.assertEquals(6, state.getState().size());
        Assertions.assertEquals("[1, 2, 14, 15.5, 19.8, 0]", state.getState().toString());
    }

    @Test
    protected void testClearOperation() {

        // Arrange
        final String[] input = {"1", "2", "14", "15.5", "19.8", "0", "Clear"};

        // Act
        stateTransit(input);

        // Assert
        Assertions.assertTrue(state.getState().isEmpty());
        Assertions.assertEquals("[]", state.getState().toString());
    }

    @Test
    protected void testClearOperationBeforeAdds() {

        // Arrange
        final String[] input = {"1", "2", "+", "15.5", "19.8", "0", "Clear", "20.123456789012", "21", "+"};

        // Act
        stateTransit(input);

        // Assert
        Assertions.assertEquals(1, state.getState().size());
        Assertions.assertEquals("[41.123456789]", state.getState().toString());
    }

    @Test
    protected void testInsufficientParams() {
        // Arrange
        final String[] input = {"2", "+"};

        // Act + Assert
        Assertions.assertThrows(InsufficientParameterException.class, () -> stateTransit(input));
    }

    /**
     * state transition operations with input.
     * @param input
     */
    private void stateTransit(final String[] input) {
        final List<RpnOperator> operators =
            Arrays.asList(input)
                .stream()
                .map(rpnOperatorFactory::getRpnOperatorFromString)
                .collect(Collectors.toList());

        state.transit(operators);
    }
}
