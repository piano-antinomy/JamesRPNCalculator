package james.rpn.calculator.element.operator.api;

/**
 * used for scenarios when user parses invalid RpnOperator.
 * @author James Ding
 * @email james.yingjian.ding@gmail.com
 *
 * Aug 2, 2020
 */
public class InvalidRpnOperatorException extends IllegalArgumentException {
    private static final long serialVersionUID = 6165409678738273454L;

    /**
     * constructor.
     * @param msg
     * @param t
     */
    public InvalidRpnOperatorException(final String msg, final Throwable t) {
        super(msg, t);
    }
}
