package james.rpn.calculator.state.api;

import java.util.List;
import java.util.Stack;

import james.rpn.calculator.element.operator.api.RpnOperator;

/**
 * Captures state transition while Calculation in progress.
 * @author James Ding
 * @email james.yingjian.ding@gmail.com
 *
 * Aug 2, 2020
 */
public interface RpnState {

    /**
     * get current state as stack.
     * @return
     */
    Stack<RpnOperator> getState();

    /**
     * transit to next State after operation provided by operator.
     * @param operator
     */
    void transit(RpnOperator operator);

    /**
     * transit to next State after operation provided by operators.
     * @param operators
     */
    void transit(List<RpnOperator> operators);
}
