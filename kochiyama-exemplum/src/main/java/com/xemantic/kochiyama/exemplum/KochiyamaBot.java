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

package com.xemantic.kochiyama.exemplum;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.xemantic.kochiyama.exemplum.conf.Configuration;
import com.xemantic.kochiyama.exemplum.conf.MissingConfigurationException;
import com.xemantic.kochiyama.exemplum.webdriver.WebDriverModule;
import com.xemantic.kochiyama.lust.KochiyamaAgent;
import com.xemantic.kochiyama.metacognition.MetacognitionModule;
import com.xemantic.kochiyama.metacognition.console.MetacognitionConsoleModule;
import org.openqa.selenium.WebDriver;

public class KochiyamaBot {

  public static void main(String... args) {
    Configuration configuration =
        Configuration.fromProperties().orElseThrow(MissingConfigurationException::new);
    configuration.setWebDriverSystemPath();

    Injector webDriverInjector = Guice.createInjector(new WebDriverModule(configuration));
    WebDriver webDriver = webDriverInjector.getInstance(WebDriver.class);
    try {
      Injector injector =
          Guice.createInjector(
              new KochiyamaModule(configuration, webDriver),
              new MetacognitionModule(),
              new MetacognitionConsoleModule());
      injector.getInstance(KochiyamaAgent.class).start();
    } finally {
      webDriver.close();
    }
  }
}
