package james.rpn.calculator.element.operator.api;

import java.util.Stack;

/**
 * represents a RPN number.
 *
 * @author James Ding
 * @email james.yingjian.ding@gmail.com
 *
 * Aug 2, 2020
 */
public abstract class RpnNumber implements RpnOperator {

    /**
     * get Operator Type.
     * @return
     */
    @Override
    public RpnOperatorTypes getOperatorType() {
        return RpnOperatorTypes.Number;
    }

    public Number getValue() {
        throw new IllegalStateException("not implemented");
    }

    /**
     * the print value for number.
     * @return
     */
    public String getPrintValue() {
        throw new IllegalStateException("not implemented");
    }

    @Override
    public Stack<RpnOperator> act(final Stack<RpnOperator> stack) {
        @SuppressWarnings("unchecked")
        final Stack<RpnOperator> newStack = (Stack<RpnOperator>) stack.clone();
        newStack.push(this);

        return newStack;
    }
}
