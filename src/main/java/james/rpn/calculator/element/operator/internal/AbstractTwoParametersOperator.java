package james.rpn.calculator.element.operator.internal;

import java.util.Stack;

import james.rpn.calculator.element.operator.api.InsufficientParameterException;
import james.rpn.calculator.element.operator.api.RpnNumber;
import james.rpn.calculator.element.operator.api.RpnOperator;
import james.rpn.calculator.stack.api.RpnStack;

/**
 * abstract operator for two params operators like "+ -  * /"
 *
 * @author James Ding
 * @email james.yingjian.ding@gmail.com
 *
 * Aug 6, 2020
 */
abstract class AbstractTwoParametersOperator implements RpnOperator {
    private final static int ELEMENTS_NUMBER = 2;
    private final String operator;

    AbstractTwoParametersOperator(final String operator) {
        this.operator = operator;
    }

    /**
     * abstract method of calculate that must be overridden.
     * @param right
     * @param left
     * @return
     */
    abstract protected RpnNumber calculate(final RpnNumber left, final RpnNumber right);

    @Override
    public RpnStack act(final RpnStack rpnStack) {
        if (rpnStack.getStack().size() < ELEMENTS_NUMBER) {
            throw new InsufficientParameterException(operator);
        }
        @SuppressWarnings("unchecked")
        final Stack<RpnOperator> newStack = (Stack<RpnOperator>) (rpnStack.getStack().clone());
        final RpnNumber right = (RpnNumber) newStack.pop();
        final RpnNumber left = (RpnNumber) newStack.pop();

        newStack.push(calculate(left, right));

        return new RpnStack(this, rpnStack, newStack);
    }

}
