package Sorters;

import Sorters.VisualizerData.VisualizerStep;

import java.awt.*;
import java.util.ArrayList;

public class MergeSort implements Sorter {
    private int[] temp;
    @Override
    public ArrayList<VisualizerStep> sort(int[] arr) {
        ArrayList<VisualizerStep> history = new ArrayList<>();
        temp = new int[arr.length];

        VisualizerStep first = VisualizerStep.buildFromArray(arr);
        history.add(first);

        topDown(history, arr, 0, arr.length - 1);

        VisualizerStep last = VisualizerStep.buildFromArray(arr);
        history.add(last);

        Util.addFinalAnimation(history);

        return history;
    }

    private void topDown(ArrayList<VisualizerStep> history, int[] arr, int startIndex, int endIndex) {
        int middle = (startIndex + endIndex) / 2;
        if (startIndex < endIndex) {
            topDown(history, arr, startIndex, middle);
            topDown(history, arr, middle + 1, endIndex);
            mergeStep(history, arr, startIndex, endIndex);
        }
    }

    private void mergeStep(ArrayList<VisualizerStep> history, int[] arr, int startIndex, int endIndex) {
        int middle = (startIndex + endIndex) / 2;
        int i = startIndex;
        int j = middle + 1;
        for (int k = startIndex; k <= endIndex; k++) {
            temp[k] = arr[k];
        }
        for (int k = startIndex; k <= endIndex; k++) {
            if (i > middle) {
                arr[k] = temp[j];

                j++;
            } else if (j > endIndex) {
                arr[k] = temp[i];

                i++;
            } else if (temp[j] < temp[i]) {
                arr[k] = temp[j];

                j++;
            } else {
                arr[k] = temp[i];

                i++;
            }

            VisualizerStep new_step = VisualizerStep.buildFromArray(arr);
            new_step.delayMultiplier = 2.0;
            for (int l = startIndex; l <= endIndex; l++) {
                new_step.elements[l].color = Color.YELLOW;
            }
            new_step.elements[k].color = Color.BLUE;
            history.add(new_step);
        }
    }
}
