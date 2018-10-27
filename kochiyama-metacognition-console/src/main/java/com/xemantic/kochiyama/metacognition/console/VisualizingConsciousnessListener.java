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

package com.xemantic.kochiyama.metacognition.console;

import com.xemantic.kochiyama.metacognition.ConsciousnessListener;
import com.xemantic.kochiyama.psyche.MentalActivity;
import java.awt.BorderLayout;
import java.lang.reflect.Method;
import javax.inject.Singleton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

@Singleton
public class VisualizingConsciousnessListener implements ConsciousnessListener {

  private final JFrame frame = new JFrame();

  private final JTextArea textArea = new JTextArea();

  public VisualizingConsciousnessListener() {
    JScrollPane scrollPane = new JScrollPane(textArea);
    frame.add(scrollPane, BorderLayout.CENTER);
    frame.setSize(200, 800);
    frame.setVisible(true);
  }

  @Override
  public void listen(
      MentalActivity activity, Class<? extends MentalActivity> activitySuperClass, Method method) {

    textArea.append(activitySuperClass.getSimpleName() + " - " + method.getName() + "\n");
  }
}
