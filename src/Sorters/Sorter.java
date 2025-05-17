package Sorters;

import Sorters.VisualizerData.VisualizerStep;

import java.util.ArrayList;

public interface Sorter {
    public ArrayList<VisualizerStep> generateSortHistory(int[] arr);
}