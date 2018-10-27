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

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.inject.AbstractModule;
import com.google.inject.name.Names;
import com.xemantic.kochiyama.exemplum.conf.Configuration;
import com.xemantic.kochiyama.lust.AmIBoredAnalysis;
import com.xemantic.kochiyama.lust.DoIWantToConceptualizeAnotherPersonaAnalysis;
import com.xemantic.kochiyama.lust.DoIWantToUnrollEvenMorePossibilitiesAnalysis;
import com.xemantic.kochiyama.lust.GoBackIntent;
import com.xemantic.kochiyama.lust.IsThereMorePotentialityAnalysis;
import com.xemantic.kochiyama.lust.LetMeInIntent;
import com.xemantic.kochiyama.lust.ShowMePeopleAsProductsIntent;
import com.xemantic.kochiyama.lust.UnrollMorePossibilitiesIntent;
import com.xemantic.kochiyama.lust.WhichFaceOrdersAndOrdainsMeAnalysis;
import com.xemantic.kochiyama.lust.basic.RandomDoIWantToConceptualizeAnotherPersonaAnalysis;
import com.xemantic.kochiyama.lust.basic.RandomDoIWantToUnrollEvenMorePossibilitiesAnalysis;
import com.xemantic.kochiyama.lust.basic.SimpleAmIBoredAnalysis;
import com.xemantic.kochiyama.memory.DiskMemory;
import com.xemantic.kochiyama.psyche.Memory;
import com.xemantic.kochiyama.sensorium.orientale.OrientaleGoBackIntent;
import com.xemantic.kochiyama.sensorium.orientale.OrientaleIsThereMorePotentialityAnalysis;
import com.xemantic.kochiyama.sensorium.orientale.OrientaleLetMeInIntent;
import com.xemantic.kochiyama.sensorium.orientale.OrientaleShowMePeopleAsProductsIntent;
import com.xemantic.kochiyama.sensorium.orientale.OrientaleUnrollMorePossibilitiesIntent;
import com.xemantic.kochiyama.sensorium.orientale.OrientaleWhichFaceOrdersAndOrdainsMeAnalysis;
import java.io.File;
import java.util.Objects;
import org.openqa.selenium.WebDriver;

public class KochiyamaModule extends AbstractModule {

  private final Configuration configuration;
  private final WebDriver webDriver;

  public KochiyamaModule(Configuration configuration, WebDriver webDriver) {
    this.configuration = Objects.requireNonNull(configuration);
    this.webDriver = webDriver;
  }

  @Override
  protected void configure() {
    bind(WebDriver.class).toInstance(webDriver);
    bind(File.class)
        .annotatedWith(Names.named("memoryStorageFolder"))
        .toInstance(new File(configuration.getProperty("memoryStorageFolder")));
    bind(String.class)
        .annotatedWith(Names.named("url"))
        .toInstance(configuration.getProperty("url"));
    bind(String.class)
        .annotatedWith(Names.named("email"))
        .toInstance(configuration.getProperty("email"));
    bind(String.class)
        .annotatedWith(Names.named("password"))
        .toInstance(configuration.getProperty("password"));
    bind(Integer.class)
        .annotatedWith(Names.named("numberOfTriesBeforeIGetBored"))
        .toInstance(configuration.getPropertyAsInteger("numberOfTriesBeforeIGetBored"));
    bind(Integer.class)
        .annotatedWith(Names.named("chanceThatIWantToConceptualizeAnotherPersona"))
        .toInstance(configuration.getPropertyAsInteger("chanceThatIWantToConceptualizeAnotherPersona"));
    bind(Integer.class)
        .annotatedWith(Names.named("chanceThatIWantToUnrollMorePossibilities"))
        .toInstance(configuration.getPropertyAsInteger("chanceThatIWantToUnrollMorePossibilities"));

    bind(LetMeInIntent.class).to(OrientaleLetMeInIntent.class);
    bind(AmIBoredAnalysis.class).to(SimpleAmIBoredAnalysis.class);
    bind(DoIWantToConceptualizeAnotherPersonaAnalysis.class).to(
        RandomDoIWantToConceptualizeAnotherPersonaAnalysis.class);
    bind(DoIWantToUnrollEvenMorePossibilitiesAnalysis.class)
        .to(RandomDoIWantToUnrollEvenMorePossibilitiesAnalysis.class);
    bind(ShowMePeopleAsProductsIntent.class).to(OrientaleShowMePeopleAsProductsIntent.class);
    bind(GoBackIntent.class).to(OrientaleGoBackIntent.class);
    bind(WhichFaceOrdersAndOrdainsMeAnalysis.class).to(OrientaleWhichFaceOrdersAndOrdainsMeAnalysis.class);
    bind(UnrollMorePossibilitiesIntent.class).to(OrientaleUnrollMorePossibilitiesIntent.class);
    bind(IsThereMorePotentialityAnalysis.class).to(OrientaleIsThereMorePotentialityAnalysis.class);
    bind(Memory.class).to(DiskMemory.class);

    bind(Gson.class)
        .toInstance(
            new GsonBuilder().excludeFieldsWithoutExposeAnnotation().setPrettyPrinting().create());
  }
}
