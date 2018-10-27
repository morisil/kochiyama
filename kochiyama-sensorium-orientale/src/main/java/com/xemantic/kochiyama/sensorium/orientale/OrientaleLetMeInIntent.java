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

import com.xemantic.kochiyama.lust.LetMeInIntent;
import java.util.List;
import javax.inject.Inject;
import javax.inject.Named;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class OrientaleLetMeInIntent implements LetMeInIntent {

  @Inject private WebDriver driver;

  @Inject
  @Named("url")
  private String url;

  @Inject
  @Named("email")
  private String email;

  @Inject
  @Named("password")
  private String password;

  @Override
  public Void act() {
    driver.get(url.replaceAll("/$", "") + "/en/auth/login");
    driver.findElement(By.name("RememberMe")).click();
    driver.findElement(By.className("email")).sendKeys(email);
    driver.findElement(By.className("password")).sendKeys(password);
    driver.findElement(By.cssSelector("div#loginButton input.button")).click();
    (new WebDriverWait(driver, 10))
        .until(ExpectedConditions.elementToBeClickable(By.className("seemore-button")));

    List<WebElement> closers = driver.findElements(By.id("closeModalWindow"));
    for (WebElement closer : closers) {
      if (closer.isDisplayed()) {
        closer.findElement(By.className("modal-icon-close")).click();
      }
    }

    return null;
  }
}
