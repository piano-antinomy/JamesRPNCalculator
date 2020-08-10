package james.rpn.calculator.element.operator.internal;

import java.math.MathContext;
import java.math.RoundingMode;
import java.util.Stack;

import james.rpn.calculator.element.operator.api.InsufficientParameterException;
import james.rpn.calculator.element.operator.api.RpnNumber;
import james.rpn.calculator.element.operator.api.RpnOperator;
import james.rpn.calculator.stack.api.RpnStack;

/**
 * abstract operator for two params operators like "+ -  * /"
 *
 * @author James Ding
 * @email james.yingjian.ding@gmail.com
 *
 * Aug 6, 2020
 */
abstract class AbstractTwoParametersOperator implements RpnOperator {
    private final static int ELEMENTS_NUMBER = 2;
    private final String operator;

    AbstractTwoParametersOperator(final String operator) {
        this.operator = operator;
    }

    @Override
    public RpnStack act(final RpnStack rpnStack) {
        if (rpnStack.getStack().size() < ELEMENTS_NUMBER) {
            throw new InsufficientParameterException(operator);
        }
        @SuppressWarnings("unchecked")
        final Stack<RpnOperator> newStack = (Stack<RpnOperator>) (rpnStack.getStack().clone());
        final RpnNumber right = (RpnNumber) newStack.pop();
        final RpnNumber left = (RpnNumber) newStack.pop();

        switch (operator) {
             case "+":
                 newStack.push(new RpnNumber(left.getValue().add(right.getValue())));
                 break;
             case "-":
                 newStack.push(new RpnNumber(left.getValue().subtract(right.getValue())));
                 break;
             case "*":
                 newStack.push(new RpnNumber(left.getValue().multiply(right.getValue(), MathContext.DECIMAL128)));
                 break;
             case "/":
                 newStack.push(
                     new RpnNumber(
                         left.getValue().divide(right.getValue(), RpnNumber.STORE_DECIMAL, RoundingMode.HALF_UP)));
                 break;
             default:
                 throw new IllegalArgumentException("Operator " + operator + " not supported");
        }


        return new RpnStack(this, rpnStack, newStack);
    }

}
