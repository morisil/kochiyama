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

package com.xemantic.kochiyama.lust;

import com.xemantic.kochiyama.ontology.Person;
import com.xemantic.kochiyama.psyche.Memory;
import javax.inject.Inject;

public class KochiyamaAgent {

  @Inject Memory memory;

  @Inject LetMeInIntent letMeInIntent;

  @Inject ShowMePeopleAsProductsIntent showMePeopleAsProductsIntent;

  @Inject UnrollMorePossibilitiesIntent unrollMorePossibilitiesIntent;

  @Inject GoBackIntent goBackIntent;

  @Inject WhichFaceOrdersAndOrdainsMeAnalysis whichFaceOrdersAndOrdainsMeAnalysis;

  @Inject IsThereMorePotentialityAnalysis isThereMorePotentialityAnalysis;

  @Inject DoIWantToUnrollEvenMorePossibilitiesAnalysis doIWantToUnrollEvenMorePossibilitiesAnalysis;

  @Inject
  DoIWantToConceptualizeAnotherPersonaAnalysis doIWantToConceptualizeAnotherPersonaAnalysis;

  @Inject AmIBoredAnalysis amIBoredAnalysis;

  public void start() {
    letMeInIntent.act();
    do {
      exerciseMyOrdoAmoris();
    } while (!amIBoredAnalysis.analyze().act());
  }

  private void exerciseMyOrdoAmoris() {
    showMePeopleAsProductsIntent.act();

    do {
      ConceptualizePersonaIntent conceptualizePersonaIntent =
          whichFaceOrdersAndOrdainsMeAnalysis.analyze();
      Person person = conceptualizePersonaIntent.act();
      memory.remember(person);
      goBackIntent.act();

      if (doIWantToUnrollEvenMorePossibilitiesAnalysis.analyze().act()
          && isThereMorePotentialityAnalysis.analyze().act()) {
        unrollMorePossibilitiesIntent.act();
      }

    } while (doIWantToConceptualizeAnotherPersonaAnalysis.analyze().act());

    goBackIntent.act();
  }
}
