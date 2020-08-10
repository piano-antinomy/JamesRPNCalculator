package james.rpn.calculator.app;

import com.google.inject.Guice;

/*
 * Main file to execute by gradle.
 */
public class Main {

    public static void main(final String[] args) {

        // spin up calculator instance and start.
        Guice.createInjector(new CalculatorStarterModule()).getInstance(CalculatorStarter.class).start();
    }

}
