package james.rpn.calculator.element.operator.internal;

import james.rpn.calculator.element.operator.api.RpnNumber;

/**
 * Subtraction operator.
 * @author James Ding
 * @email james.yingjian.ding@gmail.com
 *
 * Aug 6, 2020
 */
class SubOperator extends AbstractTwoParametersOperator {

    SubOperator() {
        super("-");
    }

    @Override
    protected RpnNumber calculate(final RpnNumber left, final RpnNumber right) {
        return new RpnNumber(left.getValue().subtract(right.getValue()));
    }
}
