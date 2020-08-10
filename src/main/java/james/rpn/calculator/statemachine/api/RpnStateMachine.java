package james.rpn.calculator.statemachine.api;

import james.rpn.calculator.state.api.RpnState;

/**
 * Rpn State Machine for Calculator.
 * @author James Ding
 * @email james.yingjian.ding@gmail.com
 *
 * Aug 9, 2020
 */
public interface RpnStateMachine {

    /**
     * parse input String as input parameters and pass to state machine.
     * @param inputString
     */
    void apply(String inputString);

    /**
     * get Current state of state machine.
     * @return
     */
    RpnState getCurrentState();
}
