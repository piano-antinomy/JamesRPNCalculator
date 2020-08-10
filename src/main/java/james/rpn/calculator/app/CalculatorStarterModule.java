package james.rpn.calculator.app;

import java.util.Scanner;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;

import james.rpn.calculator.statemachine.internal.RpnStateMachineModule;

/**
 * Provides the instance of calculator starter instance.
 * @author James Ding
 * @email james.yingjian.ding@gmail.com
 *
 * Aug 9, 2020
 */
class CalculatorStarterModule extends AbstractModule {

    @Override
    protected void configure() {
        install(new RpnStateMachineModule());
    }

    @Provides
    protected Scanner provideScanner() {
        return new Scanner(System.in);
    }
}
