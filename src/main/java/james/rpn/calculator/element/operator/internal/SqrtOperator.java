package james.rpn.calculator.element.operator.internal;

import java.math.MathContext;
import java.util.Stack;

import james.rpn.calculator.element.operator.api.InsufficientParameterException;
import james.rpn.calculator.element.operator.api.RpnNumber;
import james.rpn.calculator.element.operator.api.RpnOperator;
import james.rpn.calculator.stack.api.RpnStack;

/**
 * Calculates the square root for given value.
 * @author James Ding
 * @email james.yingjian.ding@gmail.com
 *
 * Aug 9, 2020
 */
class SqrtOperator implements RpnOperator {
    private final static int ELEMENTS_NUMBER = 1;

    @Override
    public RpnStack act(final RpnStack rpnStack) {
        if (rpnStack.getStack().size() < ELEMENTS_NUMBER) {
            throw new InsufficientParameterException("sqrt");
        }
        @SuppressWarnings("unchecked")
        final Stack<RpnOperator> newStack = (Stack<RpnOperator>) (rpnStack.getStack().clone());

        final RpnNumber oper = (RpnNumber) newStack.pop();
        final RpnNumber value = new RpnNumber(oper.getValue().sqrt(MathContext.DECIMAL128));
        newStack.push(value);

        return new RpnStack(this, rpnStack, newStack);
    }
}
