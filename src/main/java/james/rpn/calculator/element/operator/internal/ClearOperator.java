package james.rpn.calculator.element.operator.internal;

import java.util.Stack;

import james.rpn.calculator.element.operator.api.RpnOperator;
import james.rpn.calculator.element.operator.api.RpnOperatorTypes;

public class ClearOperator implements RpnOperator {

    @Override
    public int getNumberOfElements() {
        return -2;
    }

    @Override
    public Stack<RpnOperator> act(final Stack<RpnOperator> stack) {
        return null;
    }

    @Override
    public RpnOperatorTypes getOperatorType() {
        return RpnOperatorTypes.Clear;
    }

}
