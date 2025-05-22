package Sorters;

import Sorters.VisualizerData.VisualizerStep;

import java.awt.*;
import java.util.ArrayList;

public class BubbleSort implements Sorter {
    @Override
    public ArrayList<VisualizerStep> sort(int[] arr) {
        ArrayList<VisualizerStep> history = new ArrayList<>();

        history.add(VisualizerStep.buildFromArray(arr));

        for (int i = 0; i < arr.length; i++) {
            for (int j = arr.length - 2; j >= i; j--) {
                VisualizerStep new_step = VisualizerStep.buildFromArray(arr);
                new_step.elements[j].color = Color.BLUE;
                new_step.elements[j + 1].color = Color.RED;
                history.add(new_step);
                if (arr[j] > arr[j + 1]) {
                    Util.swap(arr, j, j + 1);

                    VisualizerStep inner_new_step = VisualizerStep.buildFromArray(arr);
                    inner_new_step.elements[j].color = Color.RED;
                    inner_new_step.elements[j + 1].color = Color.BLUE;
                    history.add(inner_new_step);
                }
            }

        }

        Util.addFinalAnimation(history);

        return history;
    }
}
