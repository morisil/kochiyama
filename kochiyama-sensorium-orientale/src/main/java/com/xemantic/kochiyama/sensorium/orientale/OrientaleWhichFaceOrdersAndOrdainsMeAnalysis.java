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

import com.xemantic.kochiyama.lust.ConceptualizePersonaIntent;
import com.xemantic.kochiyama.lust.WhichFaceOrdersAndOrdainsMeAnalysis;
import com.xemantic.kochiyama.psyche.Memory;
import java.util.List;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.inject.Inject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class OrientaleWhichFaceOrdersAndOrdainsMeAnalysis implements WhichFaceOrdersAndOrdainsMeAnalysis {

  private static final Pattern PROFILE_PATTERN =
      Pattern.compile("^.*/en/profile/showProfile/ID/([0-9]+).*$");

  @Inject WebDriver driver;

  @Inject Memory memory;

  private final Random random = new Random();

  @Override
  public ConceptualizePersonaIntent analyze() {
    List<WebElement> elements = driver.findElements(By.className("standardview"));
    int index = 0;
    String profileId = null;
    for (int i = 0; i < 10; i++) {
      index = random.nextInt(elements.size());
      WebElement element = elements.get(index);
      String profileLink =
          element
              .findElement(By.cssSelector("div.standardinfo div.text .hdg1 a"))
              .getAttribute("href");
      Matcher matcher = PROFILE_PATTERN.matcher(profileLink);
      matcher.matches();
      profileId = matcher.group(1);
      if (!memory.doIRemember(profileId)) {
        break;
      }
    }
    return new OrientaleConceptualizePersonaIntent(driver, elements.get(index), profileId);
  }
}
