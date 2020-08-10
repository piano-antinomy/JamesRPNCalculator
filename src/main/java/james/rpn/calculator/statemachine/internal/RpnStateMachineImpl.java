package james.rpn.calculator.statemachine.internal;

import javax.inject.Inject;

import james.rpn.calculator.element.operator.api.InsufficientParameterException;
import james.rpn.calculator.element.operator.api.RpnOperator;
import james.rpn.calculator.element.operator.api.RpnOperatorFactory;
import james.rpn.calculator.state.api.RpnState;
import james.rpn.calculator.statemachine.api.RpnStateMachine;

/**
 * State Machine for RPN calculator.
 * @author James Ding
 * @email james.yingjian.ding@gmail.com
 *
 * Aug 9, 2020
 */
class RpnStateMachineImpl implements RpnStateMachine {
    private static final String DELIMITER = " ";

    private final RpnState state;
    private final RpnOperatorFactory rpnOperatorFactory;

    @Inject
    public RpnStateMachineImpl(final RpnState state, final RpnOperatorFactory rpnOperatorFactory) {
        this.state = state;
        this.rpnOperatorFactory = rpnOperatorFactory;
    }

    @Override
    public void apply(final String inputString) {
        // add trailing space for accurate parsing.
        final String manipulatedInputString = inputString + DELIMITER;

        int wordBegin = 0;
        int wordEnd = -1;
        boolean previousSpace = false;

        /**
         * We could use string.split(" ") but we would lose position information.
         * Applies this for loop for accurate position tracking.
         */
        for (int i = 0; i < manipulatedInputString.length(); i++) {
            if (' ' == manipulatedInputString.charAt(i)) {
                if (previousSpace) {
                    continue;
                }

                wordEnd = i;
                if (wordBegin < wordEnd) {
                    final String word = manipulatedInputString.substring(wordBegin, wordEnd).trim();
                    if (word.length() > 0) {
                        final RpnOperator operator = rpnOperatorFactory.getRpnOperatorFromString(word);
                        try {
                            state.transit(operator);
                        } catch (final InsufficientParameterException e) {
                            // re-throw this exception with its position
                            // wordBegin + 1 since provided example 8 uses 1-based index.
                            throw new InsufficientParameterException(e.getOperatorName(), wordBegin + 1);
                        }
                    }
                }

                wordBegin = i + 1;
                previousSpace = true;
            } else {
                previousSpace = false;
            }
        }


    }

    @Override
    public RpnState getCurrentState() {
        return state;
    }

}
