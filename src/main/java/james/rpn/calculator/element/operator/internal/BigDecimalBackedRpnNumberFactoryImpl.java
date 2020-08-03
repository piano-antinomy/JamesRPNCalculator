package james.rpn.calculator.element.operator.internal;

import java.math.BigDecimal;
import java.math.MathContext;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import james.rpn.calculator.element.number.api.RpnNumberFactory;
import james.rpn.calculator.element.operator.api.RpnNumber;

/**
 * pkg protected factory to generate BigDecimal Rpn Number.
 * @author James Ding
 * @email james.yingjian.ding@gmail.com
 *
 * Aug 2, 2020
 */
class BigDecimalBackedRpnNumberFactoryImpl implements RpnNumberFactory<BigDecimal> {
    private static final Logger LOGGER = LogManager.getLogger(BigDecimalBackedRpnNumberFactoryImpl.class);

    @Override
    public RpnNumber<BigDecimal> generatesFromString(final String value) {
        return new BigDecimalBackedRpnNumberImpl(parseBigDecimal(value));
    }

    @Override
    public boolean isValid(final String value) {
        try {
            parseBigDecimal(value);
            return true;
        } catch (final NumberFormatException e) {
            LOGGER.error("number parsing exception: " + e);
            return false;
        }

    }

    private BigDecimal parseBigDecimal(final String value) {
        return new BigDecimal(value, MathContext.DECIMAL128);
    }

}
