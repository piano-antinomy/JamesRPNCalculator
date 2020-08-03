package james.rpn.calculator.state.internal;

import com.google.inject.AbstractModule;
import com.google.inject.Scopes;

import james.rpn.calculator.element.operator.internal.RpnOperatorModule;
import james.rpn.calculator.state.api.RpnState;

/**
 * State module to inject all dependencies.
 * @author James Ding
 * @email james.yingjian.ding@gmail.com
 *
 * Aug 2, 2020
 */
public class RpnStateModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(RpnState.class).to(RpnStateImpl.class).in(Scopes.SINGLETON);

        install(new RpnOperatorModule());
    }

}
