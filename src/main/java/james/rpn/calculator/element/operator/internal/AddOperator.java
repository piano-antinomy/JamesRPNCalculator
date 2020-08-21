package james.rpn.calculator.element.operator.internal;

import james.rpn.calculator.element.operator.api.RpnNumber;

/**
 * Add Operator.
 *
 * @author James Ding
 * @email james.yingjian.ding@gmail.com
 *
 * Aug 2, 2020
 */
class AddOperator extends AbstractTwoParametersOperator {

    AddOperator() {
        super("+");
    }

    @Override
    protected RpnNumber calculate(final RpnNumber right, final RpnNumber left) {
        return new RpnNumber(left.getValue().add(right.getValue()));
    }
}
