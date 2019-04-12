package ognev.review.components;

import dagger.Component;
import javax.inject.Singleton;
import ognev.review.modules.MyModuleMock;
import ognev.review.modules.NetworkModule;

/**
 *
 */
@Singleton
@Component(modules = {MyModuleMock.class, NetworkModule.class})
public interface MyComponentMock extends MyComponent {
}
