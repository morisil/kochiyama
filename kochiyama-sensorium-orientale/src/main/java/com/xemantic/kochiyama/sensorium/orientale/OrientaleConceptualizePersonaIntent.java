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

import com.google.common.collect.ImmutableList;
import com.google.common.io.Resources;
import com.xemantic.kochiyama.lust.ConceptualizePersonaIntent;
import com.xemantic.kochiyama.ontology.Image;
import com.xemantic.kochiyama.ontology.Person;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class OrientaleConceptualizePersonaIntent implements ConceptualizePersonaIntent {

  private static final Pattern NICK_AND_AGE_PATTERM = Pattern.compile("^(.+)\\s\\(([0-9]+)\\)\\s$");

  private static final Pattern IMAGE_URL_PATTERM =
      Pattern.compile("^background-image: url\\(\"(.*)\"\\).+$");

  private final WebDriver driver;

  private final WebElement element;

  private final String profileId;

  public OrientaleConceptualizePersonaIntent(WebDriver driver, WebElement element, String profileId) {
    this.driver = driver;
    this.element = element;
    this.profileId = profileId;
  }

  @Override
  public Person act() {
    WebElement goToProfile =
        element.findElement(By.className("iconstandard")).findElement(By.tagName("a"));
    goToProfile.click();

    // wait until topright class is visible
    WebElement profileInfo = driver.findElement(By.id("profileinfo"));
    String nickAndAge = profileInfo.findElement(By.tagName("span")).getText();
    Matcher matcher = NICK_AND_AGE_PATTERM.matcher(nickAndAge);
    matcher.matches();
    String nick = matcher.group(1);
    String age = matcher.group(2);

    String description = profileInfo.findElement(By.xpath("p")).getText();

    List<Image> images = getImages();

    return new Person(profileId, nick, Integer.parseInt(age), description, images);
  }

  private List<Image> getImages() {
    ImmutableList.Builder<Image> pictures = ImmutableList.builder();
    List<WebElement> thumbnails = driver.findElements(By.xpath("//div[@id='sliderpics']/a"));
    if (thumbnails.isEmpty()) {
      pictures.add(new Image(getData(getImageUrl()), 0));
    } else {
      for (int index = 0; index < thumbnails.size(); index++) {
        thumbnails.get(index).click();
        (new WebDriverWait(driver, 10))
            .until(ExpectedConditions.attributeContains(By.id("pic"), "style", "background-image: url"));
        pictures.add(new Image(getData(getImageUrl()), index));
      }
    }
    return pictures.build();
  }

  private byte[] getData(String url) {
    try {
      return Resources.toByteArray((new URL(url)));
    } catch (IOException e) {
      throw new RuntimeException("Could not retrieve image data", e);
    }
  }

  private String getImageUrl() {
    WebElement picture = driver.findElement(By.id("pic"));
    String pictureStyle = picture.getAttribute("style");
    Matcher matcher = IMAGE_URL_PATTERM.matcher(pictureStyle);
    if (matcher.matches()) {
      return matcher.group(1);
    } else {
      throw new RuntimeException("Could not extract image url from picture style: " + pictureStyle);
    }
  }
}
