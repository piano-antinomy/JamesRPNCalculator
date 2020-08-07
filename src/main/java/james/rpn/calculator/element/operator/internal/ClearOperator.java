package james.rpn.calculator.element.operator.internal;

import java.util.Stack;

import james.rpn.calculator.element.operator.api.InsufficientParameterException;
import james.rpn.calculator.element.operator.api.RpnOperator;
import james.rpn.calculator.element.operator.api.RpnOperatorTypes;
import james.rpn.calculator.stack.api.RpnStack;

public class ClearOperator implements RpnOperator {
    protected static final String OPERATOR = "CELAR";

    @Override
    public int getNumberOfElements() {
        return 0;
    }

    @Override
    public  RpnStack act(final RpnStack stack) {

        if (stack.getStack().empty()) {
            throw new InsufficientParameterException(OPERATOR, 0);
        }

        return new RpnStack(this, stack, new Stack<>());
    }

    @Override
    public RpnOperatorTypes getOperatorType() {
        return RpnOperatorTypes.Clear;
    }

    @Override
    public int getPosition() {
        // TODO Auto-generated method stub
        return 0;
    }

}
