package james.rpn.calculator.element.operator.api;

/**
 * factory to provide RpnOperator.
 * @author James Ding
 * @email james.yingjian.ding@gmail.com
 *
 * Aug 2, 2020
 */
public interface RpnOperatorFactory {

    /**
     * return its corresponding RpnOperator.
     *
     * @throws InvalidOperatorException if provided value is not supported.
     *
     * @param value
     * @return RpnOperator
     */
    RpnOperator getRpnOperatorFromString(final String value);
}
