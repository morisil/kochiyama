/*
 * kochiyama - model of fetishized lust
 *
 * Copyright (C) 2018  Kazimierz Pogoda
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package com.xemantic.kochiyama.metacognition;

import com.google.inject.AbstractModule;
import com.google.inject.matcher.AbstractMatcher;
import com.google.inject.matcher.Matcher;
import com.google.inject.matcher.Matchers;
import com.xemantic.kochiyama.psyche.Analysis;
import com.xemantic.kochiyama.psyche.Intent;
import java.lang.reflect.Method;
import java.util.Arrays;

public class MetacognitionModule extends AbstractModule {

  @Override
  protected void configure() {
    MentalActivityInterceptor mentalActivityInterceptor = new MentalActivityInterceptor();
    requestInjection(mentalActivityInterceptor);

    bindInterceptor(
        Matchers.subclassesOf(Intent.class),
        anyInterfaceMethod(Intent.class),
        mentalActivityInterceptor);
    bindInterceptor(
        Matchers.subclassesOf(Analysis.class),
        anyInterfaceMethod(Analysis.class),
        mentalActivityInterceptor);
  }

  private Matcher<Method> anyInterfaceMethod(Class<?> iface) {
    return new AbstractMatcher<Method>() {
      @Override
      public boolean matches(Method method) {
        return belongsToInterface(iface, method);
      }
    };
  }

  private boolean belongsToInterface(Class<?> iface, Method method) {
    return Arrays.stream(iface.getMethods()).anyMatch(m -> sameMethods(m, method));
  }

  private boolean sameMethods(Method method1, Method method2) {
    return method1.getName().equals(method2.getName())
        && method1.getReturnType().equals(method2.getReturnType())
        && Arrays.equals(method1.getParameterTypes(), method2.getParameterTypes());
  }
}
