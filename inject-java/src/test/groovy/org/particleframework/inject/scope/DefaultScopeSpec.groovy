/*
 * Copyright 2018 original authors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.particleframework.inject.scope

import org.particleframework.context.annotation.Prototype
import org.particleframework.inject.AbstractTypeElementSpec
import org.particleframework.inject.BeanDefinition
import spock.lang.Specification

import javax.inject.Singleton

/**
 * @author graemerocher
 * @since 1.0
 */
class DefaultScopeSpec extends AbstractTypeElementSpec {

    void "test default scope no override"() {
        given:"A bean that defines no explicit scope"
        when:
        BeanDefinition beanDefinition = buildBeanDefinition('test.MyBean', '''
package test;

import org.particleframework.inject.scope.*;

@SomeAnn
class MyBean {
}


''')
        then:"the default scope is singleton"
        beanDefinition.hasDeclaredStereotype(Singleton)
        beanDefinition.isSingleton()
    }

    void "test default scope with override"() {
        given:"A bean that defines no explicit scope"
        when:
        BeanDefinition beanDefinition = buildBeanDefinition('test.MyBean', '''
package test;

import org.particleframework.inject.scope.*;
import org.particleframework.context.annotation.*;

@SomeAnn
@Prototype
class MyBean {
}


''')
        then:"the default scope is singleton"
        !beanDefinition.hasDeclaredStereotype(Singleton)
        beanDefinition.hasDeclaredStereotype(Prototype)
        !beanDefinition.isSingleton()
    }
}