package Sorters;

import Sorters.VisualizerData.VisualizerStep;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class BogoSort implements Sorter {
    @Override
    public ArrayList<VisualizerStep> sort(int[] arr) {
        var history = new ArrayList<VisualizerStep>();

        history.add(VisualizerStep.buildFromArray(arr));

        while (!isSorted(arr, history)) {
            shuffle(arr, history);
        }

        Util.addFinalAnimation(history);

        return history;
    }

    private static boolean isSorted(int[] arr, ArrayList<VisualizerStep> history) {
        for (int i = 0; i < arr.length - 1; i++) {
            var new_step = VisualizerStep.buildFromArray(arr);
            new_step.elements[i].color = Color.YELLOW;
            new_step.elements[i + 1].color = Color.YELLOW;
            history.add(new_step);
            if (arr[i] > arr[i + 1]) {
                var wrong_step = VisualizerStep.buildFromArray(arr);
                for (int j = 0; j < arr.length; j++) {
                    wrong_step.elements[j].color = Color.RED;
                }
                wrong_step.delayMultiplier = 10.0;
                history.add(wrong_step);
                return false;
            }
        }
        return true;
    }

    private static void shuffle(int[] arr, ArrayList<VisualizerStep> history) {
        Random rng = new Random();
        for (int i = 0; i < arr.length - 1; i++) {
            int target = rng.nextInt(i, arr.length);
            Util.swap(arr, i, target);
            var new_step = VisualizerStep.buildFromArray(arr);
            new_step.elements[i].color = Color.RED;
            new_step.elements[target].color = Color.BLUE;
            history.add(new_step);
        }
    }
}
