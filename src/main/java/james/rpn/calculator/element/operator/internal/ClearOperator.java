package james.rpn.calculator.element.operator.internal;

import java.util.Stack;

import james.rpn.calculator.element.operator.api.InsufficientParameterException;
import james.rpn.calculator.element.operator.api.RpnOperator;
import james.rpn.calculator.stack.api.RpnStack;

/**
 * clear the current stack and start from an empty one.
 * @author James Ding
 * @email james.yingjian.ding@gmail.com
 *
 * Aug 9, 2020
 */
class ClearOperator implements RpnOperator {
    protected static final String OPERATOR = "CELAR";

    @Override
    public int getNumberOfElements() {
        return 0;
    }

    @Override
    public  RpnStack act(final RpnStack stack) {

        if (stack.getStack().empty()) {
            throw new InsufficientParameterException(OPERATOR, 0);
        }

        return new RpnStack(this, stack, new Stack<>());
    }

    @Override
    public int getPosition() {
        return 0;
    }

}
