import Sorters.SelectionSort;
import Sorters.Sorter;
import Sorters.VisualizerData.VisualizerStep;
import br.com.davidbuzatto.jsge.core.engine.EngineFrame;
import br.com.davidbuzatto.jsge.core.utils.DrawingUtils;
import br.com.davidbuzatto.jsge.image.Image;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Modelo de projeto básico da JSGE.
 * <p>
 * JSGE basic project template.
 *
 * @author Prof. Dr. David Buzatto
 */
public class Main extends EngineFrame {

    private Image logo;

    private int[] arr;
    private ArrayList<VisualizerStep> sequence;
    private int index = 0;
    private Sorter sorter;
    private int columnWidth = 12;
    private int columnGap = 10;
    private int bottomPadding = 120;
    private int topPadding = 10;
    private double stepTime = 0.02;
    private double currentStepTimer;

    public Main() {
        super(
                800,                 // largura                      / width
                450,                 // algura                       / height
                "Window Title",      // título                       / title
                60,                  // quadros por segundo desejado / target FPS
                true,                // suavização                   / antialiasing
                true,               // redimensionável              / resizable
                false,               // tela cheia                   / full screen
                false,               // sem decoração                / undecorated
                false                // sempre no topo               / always on top
        );

    }

    @Override
    public void create() {
        arr = new int[32];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = 1 + (int)(Math.random() * 30);
        }
        sequence = new ArrayList<VisualizerStep>();
        sorter = new SelectionSort();
        logo = DrawingUtils.createLogo();
        logo.resize((int) (logo.getWidth() * 0.1), (int) (logo.getWidth() * 0.1));
        setWindowIcon(logo);

        sequence = sorter.generateSortHistory(arr);
    }

    @Override
    public void update(double delta) {
        currentStepTimer += delta;
        if (currentStepTimer > stepTime * sequence.get(index).delayMultiplier) {
            currentStepTimer -= stepTime * sequence.get(index).delayMultiplier;
            if (index < sequence.size() - 1) {
                index++;
            }
        }
    }

    @Override
    public void draw() {
        clearBackground(BLACK);

        drawText(String.format("%d", index), 20.0, 20.0, 32, WHITE);
        drawVisualizerStep(sequence.get(index));

        drawFPS(120, 20);
    }

    public void drawVisualizerStep(VisualizerStep step) {
        int totalWidth = step.elements.length * (columnWidth + columnGap);
        int maxValue = Arrays
                .stream(step.elements)
                .map(x -> x.value)
                .max(Integer::compareTo)
                .get();

        for (int i = 0; i < step.elements.length; i++) {

            //double columnHeight = (double)step[i] / (double)maxValue * topPadding;
            double columnHeight = (double)(getScreenHeight() - topPadding - bottomPadding) * (double)(step.elements[i].value) / (double)maxValue;

            fillRectangle(
                    getScreenWidth() / 2 - totalWidth / 2 + i * (columnWidth + columnGap),
                    getScreenHeight() - bottomPadding - columnHeight,
                    columnWidth,
                    columnHeight,
                    step.elements[i].color
            );
        }
    }
    public void drawArray(int[] arr) {
        int totalWidth = arr.length * (columnWidth + columnGap);
        int maxValue = Arrays.stream(arr).max().getAsInt();

        for (int i = 0; i < arr.length; i++) {

            //double columnHeight = (double)arr[i] / (double)maxValue * topPadding;
            double columnHeight = (double)(getScreenHeight() - topPadding - bottomPadding) * (double)arr[i] / (double)maxValue;

            fillRectangle(
                    getScreenWidth() / 2 - totalWidth / 2 + i * (columnWidth + columnGap),
                    getScreenHeight() - bottomPadding - columnHeight,
                    columnWidth,
                    columnHeight,
                    BLACK
            );
        }
    }

    public static void main(String[] args) {
        new Main();
    }

    public void sort(int[] arr) {

    }

}
