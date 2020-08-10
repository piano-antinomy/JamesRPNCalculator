package james.rpn.calculator.statemachine.internal;

import com.google.inject.AbstractModule;
import com.google.inject.Scopes;

import james.rpn.calculator.state.internal.RpnStateModule;
import james.rpn.calculator.statemachine.api.RpnStateMachine;

/**
 * Inject all state machines dependencies.
 * @author James Ding
 * @email james.yingjian.ding@gmail.com
 *
 * Aug 9, 2020
 */
public class RpnStateMachineModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(RpnStateMachine.class).to(RpnStateMachineImpl.class).in(Scopes.SINGLETON);

        install(new RpnStateModule());
    }

    @Override
    public int hashCode() {
        return this.getClass().hashCode();
    }

    @Override
    public boolean equals(final Object that) {
        return (that != null) && this.getClass().equals(that.getClass());
    }
}
