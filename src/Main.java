import Sorters.*;
import Sorters.VisualizerData.VisualizerStep;
import br.com.davidbuzatto.jsge.core.engine.EngineFrame;
import br.com.davidbuzatto.jsge.core.utils.DrawingUtils;
import br.com.davidbuzatto.jsge.image.Image;

import java.util.*;

public class Main extends EngineFrame {

    private Image logo;

    private int[] arr;
    public ArrayList<VisualizerStep> sequence;
    public int index = 0;
    public int elementCount;
    public String currentSorter;
    public HashMap<String, Sorter> sorters;

    private int columnWidth = 16;
    private int columnGap = 4;

    public double stepTime = 0.02;
    public double currentStepTimer;
    public boolean executing;

    public int menuHeight = 220;
    public int topPadding = 24;

    private Menu menu;

    public Main() {
        super( 800, 600, "Window Title", 60, true, true, false, false, false );
    }

    @Override
    public void create() {
        sorters = new HashMap<>();
        sorters.put("Selection Sort", new SelectionSort());
        sorters.put("Insertion Sort", new InsertionSort());
        sorters.put("Bubble Sort", new BubbleSort());
        sorters.put("Merge Sort", new MergeSort());
        currentSorter = "Selection Sort";
        elementCount = 32;

        initializeSort();

        menu = new Menu(this);
    }

    public void initializeSort() {
        index = 0;
        arr = new int[elementCount];
        ArrayList<Integer> temp = new ArrayList<Integer>();
        for (int i = 0; i < arr.length; i++) {
            temp.add(i + 1);
        }
        Collections.shuffle(temp);
        for (int i = 0; i < arr.length; i++) {
            arr[i] = temp.get(i);
        }
        sequence = new ArrayList<VisualizerStep>();
        logo = DrawingUtils.createLogo();
        logo.resize((int) (logo.getWidth() * 0.1), (int) (logo.getWidth() * 0.1));
        setWindowIcon(logo);

        sequence = sorters.get(currentSorter).sort(arr);

    }

    @Override
    public void update(double delta) {
        if (isKeyPressed(KEY_RIGHT)) {
            if (index < sequence.size() - 1)
                index++;
        }
        if (isKeyPressed(KEY_LEFT)) {
            if (index > 0)
                index--;
        }
        if (isKeyDown(KEY_Q)) {
            if (elementCount > 10) {
                menu.elementCountSpinner.setValue(--elementCount);
            }
        }
        if (isKeyDown(KEY_W)) {
            if (elementCount < 500) {
                menu.elementCountSpinner.setValue(++elementCount);
            }
        }
        if (isKeyPressed(KEY_SPACE)) {
            executing = !executing;
        }
        if (executing) {
            currentStepTimer += delta;
        }
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
        if (sequence != null && !sequence.isEmpty())
            drawVisualizerStep(sequence.get(index));

        menu.draw(this);
    }

    public void drawVisualizerStep(VisualizerStep step) {
        int totalWidth = step.elements.length * (columnWidth + columnGap);
        double effectiveColumnWidth;
        double effectiveColumnGap;

        if (totalWidth > getScreenWidth() - topPadding * 2) {
            effectiveColumnWidth = columnWidth * ((double)getScreenWidth() -  topPadding * 2) / totalWidth;
            effectiveColumnGap = columnGap * ((double)getScreenWidth() -  topPadding * 2) / totalWidth;
            totalWidth = getScreenWidth() - topPadding * 2;
        } else {
            effectiveColumnWidth = columnWidth;
            effectiveColumnGap = columnGap;
        }

        int maxValue = Arrays.stream(arr).max().getAsInt();

        for (int i = 0; i < step.elements.length; i++) {

            double columnHeight = (double)(getScreenHeight() - topPadding - menuHeight) * (double)(step.elements[i].value) / (double)maxValue;

            fillRectangle(
                    getScreenWidth() / 2 - totalWidth / 2 + i * (effectiveColumnWidth + effectiveColumnGap),
                    getScreenHeight() - menuHeight - columnHeight,
                    effectiveColumnWidth,
                    columnHeight,
                    step.elements[i].color
            );
        }
    }
    public void drawArray(int[] arr) {
        int totalWidth = arr.length * (columnWidth + columnGap);
        int maxValue = Arrays.stream(arr).max().getAsInt();

        for (int i = 0; i < arr.length; i++) {

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
