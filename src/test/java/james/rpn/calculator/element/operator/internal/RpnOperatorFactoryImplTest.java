package james.rpn.calculator.element.operator.internal;

import java.util.Map;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.google.common.collect.ImmutableMap;

import james.rpn.calculator.element.operator.api.InvalidRpnOperatorException;
import james.rpn.calculator.element.operator.api.RpnNumber;
import james.rpn.calculator.element.operator.api.RpnOperator;
import james.rpn.calculator.element.operator.api.RpnOperatorFactory;

public class RpnOperatorFactoryImplTest {

    Map<String, RpnOperator> registry = ImmutableMap.<String, RpnOperator>builder()
        .put("+", new AddOperator())
        .put("-", new SubOperator())
        .put("*", new MulOperator())
        .put("/", new DivOperator())
        .put("sqrt", new SqrtOperator())
        .put("clear", new ClearOperator())
        .put("undo", new UndoOperator())
        .build();

    private final RpnOperatorFactory target = new RpnOperatorFactoryImpl(registry);

    @Test
    protected void test_factoryReturnsRpnNumer() {
        // Arrange + Act
        final RpnOperator rpnOperator = target.getRpnOperatorFromString("8");

        // Asserts
        Assertions.assertTrue(rpnOperator instanceof RpnNumber);
    }

    @Test
    protected void test_factoryReturnsOperator() {
        // Arrange + Act
        final RpnOperator rpnOperator = target.getRpnOperatorFromString("*");

        // Asserts
        Assertions.assertTrue(rpnOperator instanceof MulOperator);
    }

    @Test
    protected void test_factoryThrowsForInvalidInput() {
        Assertions.assertThrows(InvalidRpnOperatorException.class, () -> target.getRpnOperatorFromString("&&"));
    }
}
