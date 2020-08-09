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
     * returns current operator which drives the transition from previous to current.
     *
     * @return RpnOperator
     */
    public RpnOperator getCurrentRpnOperator() {
        return currentRpnOperator;
    }

    /**
     *  returns previous stack before the operator's transition.
     *
     * @return RpnStack
     */
    public RpnStack getPrevious() {
        return previous;
    }

    /**
     * returns the current stack.
     *
     * @return Stack<RpnOperator>
     */
    public Stack<RpnOperator> getStack() {
        return stack;
    }
}
