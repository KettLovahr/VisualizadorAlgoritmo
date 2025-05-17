package Sorters.VisualizerData;

public class VisualizerStep {
    public VisualizerElementBar[] elements;
    public double delayMultiplier;

    public VisualizerStep() {
        delayMultiplier = 1.0;
    }

    public static VisualizerStep buildFromArray(int[] array) {
        VisualizerStep step = new VisualizerStep();
        step.elements = new VisualizerElementBar[array.length];

        for (int i = 0; i < array.length; i++) {
            step.elements[i] = new VisualizerElementBar(array[i]);
        }

        return step;
    }

    public int[] toArray() {
        int[] arr = new int[elements.length];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = elements[i].value;
        }
        return arr;
    }
}
