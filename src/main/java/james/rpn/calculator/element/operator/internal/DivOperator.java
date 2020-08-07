package james.rpn.calculator.element.operator.internal;

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
    public int getPosition() {
        return 0;
    }

}
