package james.rpn.calculator.element.operator.api;

import java.util.Stack;

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
     * get Operator Type.
     * @return
     */
    RpnOperatorTypes getOperatorType();

    /**
     * returns the number of elements required for its operation.
     * @return
     */
    int getNumberOfElements();

    /**
     * acts on the provided stack and returns its result.
     * @param stack
     */
    Stack<RpnOperator> act(Stack<RpnOperator> stack);

    /**
     * returns its poistion.
     */
    int getPosition();
}
