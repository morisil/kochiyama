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

package com.xemantic.kochiyama.exemplum.webdriver;

import com.google.inject.AbstractModule;
import com.xemantic.kochiyama.exemplum.conf.Configuration;
import java.util.Objects;
import javax.inject.Singleton;
import org.openqa.selenium.WebDriver;

public class WebDriverModule extends AbstractModule {

  private final WebDriverProvider webDriverProvider;

  public WebDriverModule(Configuration configuration) {
    Objects.requireNonNull(configuration);
    webDriverProvider = configuration.getWebDriverProvider();
  }

  @Override
  protected void configure() {
    bind(WebDriver.class).toProvider(webDriverProvider.getWebDriverProvider()).in(Singleton.class);
  }
}
