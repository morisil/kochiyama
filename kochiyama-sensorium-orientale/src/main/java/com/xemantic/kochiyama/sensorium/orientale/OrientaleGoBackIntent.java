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

package com.xemantic.kochiyama.sensorium.orientale;

import com.xemantic.kochiyama.lust.GoBackIntent;
import javax.inject.Inject;
import org.openqa.selenium.WebDriver;

public class OrientaleGoBackIntent implements GoBackIntent {

  @Inject private WebDriver driver;

  @Override
  public Void act() {
    driver.navigate().back();
    return null;
  }
}
