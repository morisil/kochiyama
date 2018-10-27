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

package com.xemantic.kochiyama.lust.basic;

import com.google.common.base.Preconditions;
import com.xemantic.kochiyama.lust.DecidingNowIntent;
import com.xemantic.kochiyama.lust.DoIWantToConceptualizeAnotherPersonaAnalysis;
import java.util.Random;
import javax.inject.Inject;
import javax.inject.Named;

public class RandomDoIWantToConceptualizeAnotherPersonaAnalysis implements
    DoIWantToConceptualizeAnotherPersonaAnalysis {

  private static final Random RANDOM = new Random();

  private final int chanceThatIWantToConceptualizeAnotherPersona;

  @Inject
  public RandomDoIWantToConceptualizeAnotherPersonaAnalysis(
      @Named("chanceThatIWantToConceptualizeAnotherPersona")
          int chanceThatIWantToConceptualizeAnotherPersona) {

    Preconditions.checkArgument(
        (chanceThatIWantToConceptualizeAnotherPersona >= 0)
            && (chanceThatIWantToConceptualizeAnotherPersona <= 100),
        "chanceThatIWantToConceptualizeAnotherPersona should be between 0 and 100");

    this.chanceThatIWantToConceptualizeAnotherPersona =
        chanceThatIWantToConceptualizeAnotherPersona;
  }

  @Override
  public DecidingNowIntent analyze() {
    return new SimpleDecidingNowIntent(
        chanceThatIWantToConceptualizeAnotherPersona >= RANDOM.nextInt(100));
  }
}
