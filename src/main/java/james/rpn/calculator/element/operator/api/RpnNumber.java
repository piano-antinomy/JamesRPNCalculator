package james.rpn.calculator.element.operator.api;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Stack;

/**
 * represents a RPN number.
 *
 * @author James Ding
 * @email james.yingjian.ding@gmail.com
 *
 * Aug 2, 2020
 */
public class RpnNumber implements RpnOperator {

    /**
     * TODO: move constants to configurations.
     */
    protected static final int DISPLAY_DECIMAL = 10;
    protected static final int STORE_DECIMAL = 15;

    private final BigDecimal value;

    /**
     * pkg protected constructor.
     */
    public RpnNumber(final BigDecimal value) {
        this.value = value;
    }

    public BigDecimal getValue() {
        return value;
    }

    @Override
    public int getNumberOfElements() {
        throw new IllegalStateException("should not be invoked");
    }

    @Override
    public int getPosition() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public Stack<RpnOperator> act(final Stack<RpnOperator> stack) {
        @SuppressWarnings("unchecked")
        final Stack<RpnOperator> newStack = (Stack<RpnOperator>) stack.clone();
        newStack.push(this);

        return newStack;
    }

    @Override
    public RpnOperatorTypes getOperatorType() {
        return RpnOperatorTypes.Number;
    }

    @Override
    public String toString() {
        return value.setScale(DISPLAY_DECIMAL, RoundingMode.HALF_EVEN).stripTrailingZeros().toString();
    }
}
