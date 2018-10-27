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

import javax.inject.Provider;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public enum WebDriverProvider {
  CHROME(ChromeDriver::new),
  FIREFOX(FirefoxDriver::new);

  private final Provider<WebDriver> webDriverProvider;

  WebDriverProvider(Provider<WebDriver> webDriverSupplier) {
    this.webDriverProvider = webDriverSupplier;
  }

  public Provider<WebDriver> getWebDriverProvider() {
    return webDriverProvider;
  }
}
