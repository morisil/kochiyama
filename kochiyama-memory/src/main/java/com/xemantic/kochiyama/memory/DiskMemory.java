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

package com.xemantic.kochiyama.memory;

import com.google.common.io.Files;
import com.google.gson.Gson;
import com.xemantic.kochiyama.ontology.Image;
import com.xemantic.kochiyama.ontology.Person;
import com.xemantic.kochiyama.psyche.Memory;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Objects;
import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;

@Singleton
public class DiskMemory implements Memory {

  private final File memoryStorageFolder;

  private final Gson gson;

  @Inject
  public DiskMemory(@Named("memoryStorageFolder") File memoryStorageFolder, Gson gson) {
    this.memoryStorageFolder = memoryStorageFolder;
    this.gson = gson;
  }

  @Override
  public void remember(Person person) {
    Objects.requireNonNull(person, "profileId cannot be null");
    String profileId = person.getProfileId();
    File profileDir = assureProfileDir(profileId);
    saveJson(person, getProfileFile(profileDir, profileId));
    for (Image image : person.getImages()) {
      saveImage(profileDir, profileId, image);
    }
  }

  @Override
  public boolean doIRemember(String profileId) {
    Objects.requireNonNull(profileId, "profileId cannot be null");
    File profileDir = new File(memoryStorageFolder, profileId);
    return profileDir.exists();
  }

  private File assureProfileDir(String profileId) {
    File profileDir = new File(memoryStorageFolder, profileId);
    profileDir.mkdir();
    return profileDir;
  }

  private File getProfileFile(File profileDir, String profileId) {
    return new File(profileDir, profileId + ".json");
  }

  private void saveJson(Person person, File file) {
    String json = gson.toJson(person);
    try {
      Files.asCharSink(file, StandardCharsets.UTF_8).write(json);
    } catch (IOException e) {
      throw new RuntimeException("Could not write JSON for profile: " + person.getProfileId(), e);
    }
  }

  private void saveImage(File profileDir, String profileId, Image image) {
    File pictureFile = new File(profileDir, profileId + "-" + image.getIndex() + ".jpg");
    try {
      Files.write(image.getData(), pictureFile);
    } catch (IOException e) {
      throw new RuntimeException(
          "Could not write picture file: " + pictureFile.getAbsolutePath(), e);
    }
  }
}
