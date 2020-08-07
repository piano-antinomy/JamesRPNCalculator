package james.rpn.calculator.state.internal;

import java.util.List;
import java.util.Stack;

import james.rpn.calculator.element.operator.api.RpnOperator;
import james.rpn.calculator.stack.api.RpnStack;
import james.rpn.calculator.state.api.RpnState;

/**
 * pkg protected class for state transition.
 *
 * @author James Ding
 * @email james.yingjian.ding@gmail.com
 *
 * Aug 2, 2020
 */
class RpnStateImpl implements RpnState {
    /**
     * states represent all states for the underlying stack.
     */
    private RpnStack state;

    RpnStateImpl() {
        state = new RpnStack(null, null, new Stack<RpnOperator>());
    }

    @Override
    public RpnStack getState() {
        return state;
    }

    @Override
    public void transit(final RpnOperator operator) {
        state = operator.act(state);
    }

    @Override
    public void transit(final List<RpnOperator> operators) {
        operators.forEach(this::transit);
    }

}
