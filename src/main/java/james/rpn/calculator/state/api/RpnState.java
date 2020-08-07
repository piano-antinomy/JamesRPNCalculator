package james.rpn.calculator.state.api;

import java.util.List;

import james.rpn.calculator.element.operator.api.RpnOperator;
import james.rpn.calculator.stack.api.RpnStack;

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
    RpnStack getState();

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
