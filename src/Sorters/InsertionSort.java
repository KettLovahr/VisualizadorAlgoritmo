package Sorters;

import Sorters.VisualizerData.VisualizerStep;

import java.awt.*;
import java.util.ArrayList;

public class InsertionSort implements Sorter {
    @Override
    public ArrayList<VisualizerStep> sort(int[] arr) {
        ArrayList<VisualizerStep> history = new ArrayList<VisualizerStep>();

        history.add(VisualizerStep.buildFromArray(arr));

        for (int i = 0; i < arr.length; i++) {
            int j = i;
            while (j > 0 && arr[j - 1] > arr[j]){
                Util.swap(arr, j, j - 1);
                VisualizerStep new_step = VisualizerStep.buildFromArray(arr);
                new_step.elements[j].color = Color.RED;
                new_step.elements[j - 1].color = Color.YELLOW;
                new_step.elements[i].color = Color.BLUE;
                history.add(new_step);

                j--;
            }
        }

        Util.addFinalAnimation(history);

        return history;
    }
}
