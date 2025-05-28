package Sorters;

import Sorters.VisualizerData.VisualizerStep;

import java.awt.*;
import java.util.ArrayList;

public class Util {
    public static void swap(int[] arr, int i1, int i2) {
        int temp = arr[i1];
        arr[i1] = arr[i2];
        arr[i2] = temp;
    }

    public static void addFinalAnimation(ArrayList<VisualizerStep> history) {
        VisualizerStep last_element = history.get(history.size() - 1);
        for (int i = 0; i < last_element.elements.length; i++) {
            VisualizerStep new_step = VisualizerStep.buildFromArray(last_element.toArray());
            for (int j = 0; j <= i; j++) {
                new_step.elements[j].color = Color.GREEN;
            }
            history.add(new_step);
        }
    }
}
