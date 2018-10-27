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

package com.xemantic.kochiyama.exemplum.conf;

import com.xemantic.kochiyama.exemplum.KochiyamaBot;
import com.xemantic.kochiyama.exemplum.webdriver.WebDriverProvider;
import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;
import java.util.Optional;
import java.util.Properties;

/** Wraps standard Java {@link Properties} for more convenient access to configuration options. */
public class Configuration {

  private final Properties properties;

  public Configuration(Properties properties) {
    this.properties = Objects.requireNonNull(properties);
  }

  public String getProperty(String name) {
    return Optional.ofNullable(properties.getProperty(name))
        .orElseThrow(() -> new MissingPropertyException(name));
  }

  public Integer getPropertyAsInteger(String name) {
    return Integer.valueOf(getProperty(name));
  }

  public WebDriverProvider getWebDriverProvider() {
    return WebDriverProvider.valueOf(getProperty("webDriverProvider"));
  }

  public void setWebDriverSystemPath() {
    switch (getWebDriverProvider()) {
      case CHROME:
        System.setProperty("webdriver.chrome.driver", getProperty("chromeWebDriverPath"));
        break;
      case FIREFOX:
        System.setProperty("webdriver.gecko.driver", getProperty("geckoWebDriverPath"));
        break;
    }
  }

  public static Optional<Configuration> fromProperties() {
    try (InputStream in = KochiyamaBot.class.getResourceAsStream("/kochiyama.properties")) {
      return Optional.ofNullable(in).map(Configuration::loadProperties).map(Configuration::new);
    } catch (IOException e) {
      throw new ConfigurationLoadingException(e); // should never happen
    }
  }

  private static Properties loadProperties(InputStream in) {
    Properties properties = new Properties();
    try {
      properties.load(in);
    } catch (IOException e) {
      throw new ConfigurationLoadingException(e); // should never happen
    }
    return properties;
  }
}
