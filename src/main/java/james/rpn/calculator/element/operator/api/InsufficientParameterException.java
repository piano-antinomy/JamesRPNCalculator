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

    public InsufficientParameterException(final String operatorName, final int operatorPosition) {
        super();
        this.operatorName = operatorName;
        this.operatorPosition = operatorPosition;
    }


    public String getOperatorName() {
        return operatorName;
    }

    public int getOperatorPosition() {
        return operatorPosition;
    }

}
