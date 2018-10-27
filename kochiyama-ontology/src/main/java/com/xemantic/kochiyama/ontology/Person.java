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

package com.xemantic.kochiyama.ontology;

import com.google.gson.annotations.Expose;
import java.util.List;
import java.util.Objects;

public class Person {

  @Expose private final String profileId;

  @Expose private final String nickname;

  @Expose private final int age;

  @Expose private final String description;

  private final List<Image> images;

  public Person(
      String profileId, String nickname, int age, String description, List<Image> images) {
    this.profileId = Objects.requireNonNull(profileId);
    this.nickname = Objects.requireNonNull(nickname);
    this.age = age;
    this.description = description;
    this.images = Objects.requireNonNull(images);
  }

  public String getProfileId() {
    return profileId;
  }

  public String getNickname() {
    return nickname;
  }

  public int getAge() {
    return age;
  }

  public String getDescription() {
    return description;
  }

  public List<Image> getImages() {
    return images;
  }
}
