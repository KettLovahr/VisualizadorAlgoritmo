package Sorters;

import Sorters.VisualizerData.VisualizerStep;

import java.awt.*;
import java.util.ArrayList;

public class SelectionSort implements Sorter {

    @Override
    public ArrayList<VisualizerStep> generateSortHistory(int[] arr) {
        ArrayList<VisualizerStep> history = new ArrayList<VisualizerStep>();
        history.add(VisualizerStep.buildFromArray(arr));

        for (int i = 0; i < arr.length - 1; i++) {
            int jMinimum = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] < arr[jMinimum]) {
                    jMinimum = j;
                }
                VisualizerStep new_step = VisualizerStep.buildFromArray(arr);
                new_step.elements[i].color = Color.BLUE;
                new_step.elements[j].color = Color.YELLOW;
                new_step.elements[jMinimum].color = Color.RED;
                history.add(new_step);
            }

            if (jMinimum != i) {
                Util.swap(arr, i, jMinimum);

                VisualizerStep new_step = VisualizerStep.buildFromArray(arr);
                new_step.elements[i].color = Color.RED;
                new_step.elements[jMinimum].color = Color.BLUE;
                new_step.delayMultiplier = 10.0;
                history.add(new_step);
            }
        }

        Util.addFinalAnimation(history);

        return history;
    }
}
