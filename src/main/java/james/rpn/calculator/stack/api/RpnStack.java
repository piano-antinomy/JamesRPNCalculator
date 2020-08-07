package james.rpn.calculator.stack.api;

import java.util.Stack;

import james.rpn.calculator.element.operator.api.RpnOperator;

/**
 * The stack represents a rpn stack.
 *
 * @author James Ding
 * @email james.yingjian.ding@gmail.com
 *
 * Aug 6, 2020
 */
public class RpnStack {

    private final RpnOperator currentRpnOperator;
    private final RpnStack previous;
    private final Stack<RpnOperator> stack;

    /**
     * Constructor.
     * @param currentRpnOperator
     * @param previous
     * @param stack
     */
    public RpnStack(final RpnOperator currentRpnOperator, final RpnStack previous, final Stack<RpnOperator> stack) {
        this.currentRpnOperator = currentRpnOperator;
        this.previous = previous;
        this.stack = stack;
    }

    /**
     *
     * @return
     */
    public RpnOperator getCurrentRpnOperator() {
        return currentRpnOperator;
    }

    /**
     *
     * @return
     */
    public RpnStack getPrevious() {
        return previous;
    }

    /**
     *
     * @return
     */
    public Stack<RpnOperator> getStack() {
        return stack;
    }
}
