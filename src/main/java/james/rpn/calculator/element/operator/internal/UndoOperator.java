package james.rpn.calculator.element.operator.internal;

import james.rpn.calculator.element.operator.api.InsufficientParameterException;
import james.rpn.calculator.element.operator.api.RpnOperator;
import james.rpn.calculator.stack.api.RpnStack;

/**
 * Undos the previous operator and makes the stack to previous one.
 *
 * @author James Ding
 * @email james.yingjian.ding@gmail.com
 *
 * Aug 9, 2020
 */
class UndoOperator implements RpnOperator {

    @Override
    public RpnStack act(final RpnStack stack) {
        final RpnStack previous = stack.getPrevious();

        if (previous == null) {
            throw new InsufficientParameterException("Undo");
        }

        return previous;
    }

}
