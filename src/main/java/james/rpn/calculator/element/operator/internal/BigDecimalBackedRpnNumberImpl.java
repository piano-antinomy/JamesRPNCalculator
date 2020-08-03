package james.rpn.calculator.element.operator.internal;

import java.math.BigDecimal;

import james.rpn.calculator.element.operator.api.RpnNumber;

/**
 * pkg protected class of implementation for a RpnNumber.
 *
 * @author James Ding
 * @email james.yingjian.ding@gmail.com
 *
 * Aug 2, 2020
 */
class BigDecimalBackedRpnNumberImpl extends RpnNumber {
    /**
     * TODO: move constants to configurations.
     */
    protected static final int DISPLAY_DECIMAL = 10;
    protected static final int STORE_DECIMAL = 15;

    private final BigDecimal value;

    /**
     * pkg protected constructor.
     */
    protected BigDecimalBackedRpnNumberImpl(final BigDecimal value) {
        this.value = value;
    }

    @Override
    public BigDecimal getValue() {
        return value;
    }

    @Override
    public String getPrintValue() {
        /**
         * this will NOT change the value, but only display with
         * a possibly different scales.
         */
        return value.setScale(DISPLAY_DECIMAL).toPlainString();
    }

    @Override
    public int getNumberOfElements() {
        // TODO Auto-generated method stub
        return 0;
    }
}
