package james.rpn.calculator.element.operator.internal;

import java.math.RoundingMode;

import james.rpn.calculator.element.operator.api.RpnNumber;

/**
 * Divide Operator.
 * @author James Ding
 * @email james.yingjian.ding@gmail.com
 *
 * Aug 6, 2020
 */
class DivOperator extends AbstractTwoParametersOperator {

    public DivOperator() {
        super("/");
    }

    @Override
    protected RpnNumber calculate(final RpnNumber left, final RpnNumber right) {
        return new RpnNumber(
            left.getValue().divide(right.getValue(), RpnNumber.STORE_DECIMAL, RoundingMode.HALF_UP));
    }
}
