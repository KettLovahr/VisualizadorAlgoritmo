package Sorters.VisualizerData;

import java.awt.*;

public class VisualizerElementBar {
    public int value;
    public Color color;

    public VisualizerElementBar(int value) {
        this.value = value;
        this.color = Color.WHITE;
    }
    public VisualizerElementBar(int value, Color color) {
        this.value = value;
        this.color = color;
    }
}
