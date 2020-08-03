package james.rpn.calculator.element.operator.internal;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Named;

import james.rpn.calculator.element.operator.api.InvalidRpnOperatorException;
import james.rpn.calculator.element.operator.api.RpnNumber;
import james.rpn.calculator.element.operator.api.RpnOperator;
import james.rpn.calculator.element.operator.api.RpnOperatorFactory;

/**
 * package protected class for Operator Factory.
 *
 * @author James Ding
 * @email james.yingjian.ding@gmail.com
 *
 * Aug 2, 2020
 */
class RpnOperatorFactoryImpl implements RpnOperatorFactory {

    private final Map<String, RpnOperator> operatorRegistry;

    @Inject
    RpnOperatorFactoryImpl(
        @Named(RpnOperatorModule.INJECT_NAME)
        final Map<String, RpnOperator> operatorRegistry) {
        this.operatorRegistry = operatorRegistry;
    }

    @Override
    public RpnOperator getRpnOperatorFromString(final String value) {

        final RpnOperator rpnOperator = operatorRegistry.get(value);

        if (rpnOperator != null) {
            return rpnOperator;
        }

        try {
            return new RpnNumber(new BigDecimal(value, MathContext.DECIMAL128));
        } catch (final NumberFormatException e) {
            throw new InvalidRpnOperatorException(value + " is neither a number nor an supported operator", e);
        }
    }
}
