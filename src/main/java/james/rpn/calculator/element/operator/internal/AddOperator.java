package james.rpn.calculator.element.operator.internal;

import java.util.Stack;

import james.rpn.calculator.element.operator.api.InsufficientParameterException;
import james.rpn.calculator.element.operator.api.RpnNumber;
import james.rpn.calculator.element.operator.api.RpnOperator;
import james.rpn.calculator.element.operator.api.RpnOperatorTypes;
import james.rpn.calculator.stack.api.RpnStack;

/**
 * Add Operator.
 *
 * @author James Ding
 * @email james.yingjian.ding@gmail.com
 *
 * Aug 2, 2020
 */
class AddOperator implements RpnOperator {

    @Override
    public RpnOperatorTypes getOperatorType() {
        return RpnOperatorTypes.Add;
    }

    @Override
    public int getNumberOfElements() {
        return 2;
    }

    @Override
    public RpnStack act(final RpnStack rpnStack) {
        if (rpnStack.getStack().size() < getNumberOfElements()) {
            //TODO: change this.
            throw new InsufficientParameterException("+", 0);
        }
        @SuppressWarnings("unchecked")
        final Stack<RpnOperator> newStack = (Stack<RpnOperator>) (rpnStack.getStack().clone());
        final RpnNumber left = (RpnNumber) newStack.pop();
        final RpnNumber right = (RpnNumber) newStack.pop();

        newStack.push(new RpnNumber(left.getValue().add(right.getValue())));

        return new RpnStack(this, rpnStack, newStack);
    }

    @Override
    public int getPosition() {
        return 0;
    }
}
