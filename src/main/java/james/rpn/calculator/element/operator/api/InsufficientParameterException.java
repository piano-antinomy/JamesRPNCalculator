package james.rpn.calculator.element.operator.api;

/**
 * captures insufficient parameter exception.
 *
 * @author James Ding
 * @email james.yingjian.ding@gmail.com
 *
 * Aug 2, 2020
 */
public class InsufficientParameterException extends IllegalArgumentException {
    /**
     *
     */
    private static final long serialVersionUID = 6819849175969470424L;

    private final String operatorName;
    private final int operatorPosition;

    /**
     * For state machine who knows the operator position.
     * @param operatorName
     * @param operatorPosition
     */
    public InsufficientParameterException(final String operatorName, final int operatorPosition) {
        super();
        this.operatorName = operatorName;
        this.operatorPosition = operatorPosition;
    }

    /**
     * for operator who does not know the parameter position.
     * @param operatorName
     */
    public InsufficientParameterException(final String operatorName) {
        super();
        this.operatorName = operatorName;
        operatorPosition = -1;
    }

    public String getOperatorName() {
        return operatorName;
    }

    public int getOperatorPosition() {
        return operatorPosition;
    }

}
