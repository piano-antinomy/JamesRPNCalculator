package james.rpn.calculator.element.operator.internal;

import java.math.MathContext;

import james.rpn.calculator.element.operator.api.RpnNumber;

/**
 * Multiply operator.
 * @author James Ding
 * @email james.yingjian.ding@gmail.com
 *
 * Aug 6, 2020
 */
class MulOperator extends AbstractTwoParametersOperator {

    MulOperator() {
        super("*");
    }

    @Override
    protected RpnNumber calculate(final RpnNumber left, final RpnNumber right) {
        return new RpnNumber(left.getValue().multiply(right.getValue(), MathContext.DECIMAL128));
    }
}
