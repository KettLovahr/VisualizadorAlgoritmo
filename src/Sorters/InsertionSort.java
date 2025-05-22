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
                VisualizerStep pre_step = VisualizerStep.buildFromArray(arr);
                pre_step.elements[i].color = Color.YELLOW;
                pre_step.elements[j].color = Color.BLUE;
                pre_step.elements[j - 1].color = Color.RED;
                history.add(pre_step);

                Util.swap(arr, j, j - 1);
                VisualizerStep post_step = VisualizerStep.buildFromArray(arr);
                post_step.elements[i].color = Color.YELLOW;
                post_step.elements[j].color = Color.RED;
                post_step.elements[j - 1].color = Color.BLUE;
                history.add(post_step);

                j--;
            }

            if (j > 0) {
                VisualizerStep fail_step = VisualizerStep.buildFromArray(arr);
                fail_step.elements[i].color = Color.YELLOW;
                fail_step.elements[j].color = Color.BLUE;
                fail_step.elements[j - 1].color = Color.RED;
                history.add(fail_step);

            }

        }

        Util.addFinalAnimation(history);

        return history;
    }
}
