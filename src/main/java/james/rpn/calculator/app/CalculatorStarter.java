package james.rpn.calculator.app;

import java.util.Scanner;

import javax.inject.Inject;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import james.rpn.calculator.element.operator.api.InsufficientParameterException;
import james.rpn.calculator.element.operator.api.InvalidRpnOperatorException;
import james.rpn.calculator.popup.PopUpPrinter;
import james.rpn.calculator.statemachine.api.RpnStateMachine;

/**
 * Calculator entry point.
 *
 * @author James Ding
 * @email james.yingjian.ding@gmail.com
 *
 * Aug 9, 2020
 */
class CalculatorStarter {
    private final static Logger LOGGER = LogManager.getLogger(CalculatorStarter.class);

    private final Scanner scanner;
    private final RpnStateMachine rpnStateMachine;
    private final PopUpPrinter printer;

    @Inject
    public CalculatorStarter(final Scanner scanner, final RpnStateMachine rpnStateMachine, final PopUpPrinter printer) {
        this.scanner = scanner;
        this.printer = printer;
        this.rpnStateMachine = rpnStateMachine;

    }

    /**
     * entry points to start a calculator process.
     */
    public void start() {
        printer.println("Welcome to Rpn Calculator, start input below: ");
        try {
            while (true) {
                try {
                    final String inputString = scanner.nextLine();

                    rpnStateMachine.apply(inputString);
                } catch (final InsufficientParameterException e) {
                    final String errorString = String.format(
                        "operator %s (position: %d): insufficient parameters",
                        e.getOperatorName(), e.getOperatorPosition());

                    printer.println(errorString);
                } catch (final InvalidRpnOperatorException e) {
                    printer.println(e.getMessage());
                }

                printer.println("stack: " + rpnStateMachine.getCurrentState().getState().toString());
            }
        } catch (final RuntimeException e) {
            LOGGER.fatal("Errors caused calculator to exit", e);

            throw new IllegalStateException("Calculator exits with errors: ", e);
        } finally {
            scanner.close();
        }
    }

}
