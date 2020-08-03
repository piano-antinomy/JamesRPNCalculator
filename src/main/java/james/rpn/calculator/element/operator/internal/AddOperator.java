package james.rpn.calculator.element.operator.internal;

import java.util.Stack;

import james.rpn.calculator.element.operator.api.InsufficientParameterException;
import james.rpn.calculator.element.operator.api.RpnNumber;
import james.rpn.calculator.element.operator.api.RpnOperator;
import james.rpn.calculator.element.operator.api.RpnOperatorTypes;

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
    public Stack<RpnOperator> act(final Stack<RpnOperator> stack) {
        if (stack.size() < getNumberOfElements()) {
            //TODO: change this.
            throw new InsufficientParameterException("+", 0);
        }

        final RpnNumber left = (RpnNumber) stack.pop();
        final RpnNumber right = (RpnNumber) stack.pop();

        @SuppressWarnings("unchecked")
        final Stack<RpnOperator> newStack = (Stack<RpnOperator>) (stack.clone());
        newStack.push(new RpnNumber(left.getValue().add(right.getValue())));

        return newStack;
    }

    @Override
    public int getPosition() {
        return 0;
    }

}
