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

    public SqrtOperator() {
    }

    @Override
    public int getNumberOfElements() {
        return 1;
    }

    @Override
    public RpnStack act(final RpnStack rpnStack) {
        if (rpnStack.getStack().size() < getNumberOfElements()) {
            throw new InsufficientParameterException("Sqrt");
        }
        @SuppressWarnings("unchecked")
        final Stack<RpnOperator> newStack = (Stack<RpnOperator>) (rpnStack.getStack().clone());

        final RpnNumber oper = (RpnNumber) newStack.pop();
        final RpnNumber value = new RpnNumber(oper.getValue().sqrt(MathContext.DECIMAL128));
        newStack.push(value);

        return new RpnStack(this, rpnStack, newStack);
    }

    @Override
    public int getPosition() {
        // TODO Auto-generated method stub
        return 0;
    }

}
