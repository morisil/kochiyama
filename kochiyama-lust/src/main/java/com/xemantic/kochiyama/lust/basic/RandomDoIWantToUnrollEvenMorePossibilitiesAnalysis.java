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
import com.xemantic.kochiyama.lust.DoIWantToUnrollEvenMorePossibilitiesAnalysis;
import java.util.Random;
import javax.inject.Inject;
import javax.inject.Named;

public class RandomDoIWantToUnrollEvenMorePossibilitiesAnalysis
    implements DoIWantToUnrollEvenMorePossibilitiesAnalysis {

  private static final Random RANDOM = new Random();

  private final int chanceThatIWantToUnrollMorePossibilities;

  @Inject
  public RandomDoIWantToUnrollEvenMorePossibilitiesAnalysis(
      @Named("chanceThatIWantToUnrollMorePossibilities")
          int chanceThatIWantToUnrollMorePossibilities) {

    Preconditions.checkArgument(
        (chanceThatIWantToUnrollMorePossibilities >= 0)
            && (chanceThatIWantToUnrollMorePossibilities <= 100),
        "chanceThatIWantToSeeMore should be between 0 and 100");

    this.chanceThatIWantToUnrollMorePossibilities = chanceThatIWantToUnrollMorePossibilities;
  }

  @Override
  public DecidingNowIntent analyze() {
    return new SimpleDecidingNowIntent(
        chanceThatIWantToUnrollMorePossibilities >= RANDOM.nextInt(100));
  }
}
