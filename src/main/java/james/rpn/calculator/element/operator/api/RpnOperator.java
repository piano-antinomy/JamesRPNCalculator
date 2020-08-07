package james.rpn.calculator.element.operator.api;

import james.rpn.calculator.stack.api.RpnStack;

/**
 * represents an operator for RPN Calculation.
 *
 * @author James Ding
 * @email james.yingjian.ding@gmail.com
 *
 * Aug 2, 2020
 */
public interface RpnOperator {
    /**
     * returns the number of elements required for its operation.
     * @return
     */
    int getNumberOfElements();

    /**
     * acts on the provided stack and returns its result.
     * @param stack
     */
    RpnStack act(RpnStack stack);

    /**
     * returns its poistion.
     */
    int getPosition();
}
