package james.rpn.calculator.element.operator.internal;

import java.util.Map;

import javax.inject.Named;

import com.google.common.collect.ImmutableMap;
import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Scopes;

import james.rpn.calculator.element.operator.api.RpnOperator;
import james.rpn.calculator.element.operator.api.RpnOperatorFactory;

/**
 * Module to provide impls for number and factory.
 * @author James Ding
 * @email james.yingjian.ding@gmail.com
 *
 * Aug 2, 2020
 */
public class RpnOperatorModule extends AbstractModule {

    protected static final String OPERATOR_REGISTRY = "OperatorRegistry";

    @Override
    protected void configure() {
        bind(RpnOperatorFactory.class)
            .to(RpnOperatorFactoryImpl.class)
            .in(Scopes.SINGLETON);
    }

    @Provides
    @Named(OPERATOR_REGISTRY)
    protected Map<String, RpnOperator> provideRegistry() {
        return ImmutableMap.<String, RpnOperator>builder()
            .put("+", new AddOperator())
            .put("-", new SubOperator())
            .put("*", new MulOperator())
            .put("/", new DivOperator())
            .put("sqrt", new SqrtOperator())
            .put("clear", new ClearOperator())
            .put("undo", new UndoOperator())
            .build();
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
