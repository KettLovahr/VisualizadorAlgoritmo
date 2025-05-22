import Sorters.*;
import Sorters.VisualizerData.VisualizerStep;
import br.com.davidbuzatto.jsge.core.engine.EngineFrame;
import br.com.davidbuzatto.jsge.core.utils.DrawingUtils;
import br.com.davidbuzatto.jsge.image.Image;

import java.util.*;

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
    public ArrayList<VisualizerStep> sequence;
    public int index = 0;
    public String currentSorter;
    public HashMap<String, Sorter> sorters;

    private int columnWidth = 12;
    private int columnGap = 10;

    public double stepTime = 0.02;
    public double currentStepTimer;
    public boolean executing;

    public int menuHeight = 200;
    public int topPadding = 10;

    private Menu menu;

    public Main() {
        super(
                800,               // largura                      / width
                600,                 // algura                       / height
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
        sorters = new HashMap<>();
        sorters.put("Selection Sort", new SelectionSort());
        sorters.put("Insertion Sort", new InsertionSort());
        sorters.put("Bubble Sort", new BubbleSort());
        currentSorter = "Selection Sort";

        initializeSort();

        menu = new Menu(this);
    }

    public void initializeSort() {
        index = 0;
        arr = new int[32];
        ArrayList<Integer> temp = new ArrayList<Integer>();
        for (int i = 0; i < arr.length; i++) {
            temp.add(i + 1);
            //arr[i] = 1 + (int)(Math.random() * 30);
        }
        Collections.shuffle(temp);
        for (int i = 0; i < arr.length; i++) {
            arr[i] = temp.get(i);
        }
        sequence = new ArrayList<VisualizerStep>();
        //sorter = new SelectionSort();
        logo = DrawingUtils.createLogo();
        logo.resize((int) (logo.getWidth() * 0.1), (int) (logo.getWidth() * 0.1));
        setWindowIcon(logo);

        sequence = sorters.get(currentSorter).sort(arr);

    }

    @Override
    public void update(double delta) {
        currentStepTimer += delta;
        if (currentStepTimer > stepTime * sequence.get(index).delayMultiplier && executing) {
            currentStepTimer -= stepTime * sequence.get(index).delayMultiplier;
            if (index < sequence.size() - 1) {
                index++;
            } else {
                executing = false;
            }
        }
        menu.update(this, delta);
        if (!Objects.equals(menu.currentSorter, currentSorter) && menu.currentSorter != null) {
            currentSorter = menu.currentSorter;
            initializeSort();
        }
    }

    @Override
    public void draw() {
        clearBackground(BLACK);

        drawText(String.format("%d - %s", index, currentSorter), 20.0, 20.0, 32, WHITE);
        if (sequence != null && sequence.size() > 0)
        drawVisualizerStep(sequence.get(index));

        drawFPS(120, 20);
        menu.draw(this);
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
            double columnHeight = (double)(getScreenHeight() - topPadding - menuHeight) * (double)(step.elements[i].value) / (double)maxValue;

            fillRectangle(
                    getScreenWidth() / 2 - totalWidth / 2 + i * (columnWidth + columnGap),
                    getScreenHeight() - menuHeight - columnHeight,
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
            double columnHeight = (double)(getScreenHeight() - topPadding - menuHeight) * (double)arr[i] / (double)maxValue;

            fillRectangle(
                    getScreenWidth() / 2 - totalWidth / 2 + i * (columnWidth + columnGap),
                    getScreenHeight() - menuHeight - columnHeight,
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
