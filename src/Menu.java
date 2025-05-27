import br.com.davidbuzatto.jsge.core.engine.EngineFrame;
import br.com.davidbuzatto.jsge.imgui.*;

import java.util.ArrayList;

public class Menu {
    GuiButton resetButton;
    GuiButton togglePlaybackButton;
    GuiSlider playbackScrubber;
    GuiDropdownList algorithmPicker;

    GuiLabel elementCountSpinnerLabel;
    GuiSpinner elementCountSpinner;

    public String currentSorter;

    public Menu(Main e) {
        currentSorter = "Selection Sort";
        resetButton = new GuiButton(0,0, 80, 20, "Reset", e);
        togglePlaybackButton = new GuiButton(0,0, 80, 20, "Play", e);
        playbackScrubber = new GuiSlider(0, 0, 400, 20, 0, 0, 1, e);

        elementCountSpinnerLabel = new GuiLabel(0, 0, 80, 20, "Elements:", e);
        elementCountSpinner = new GuiSpinner(0, 0, 80, 20, 32, 10, 500, e);

        ArrayList<String> algorithms = new ArrayList<String>();
        algorithms.add("Selection Sort");
        algorithms.add("Insertion Sort");
        algorithms.add("Bubble Sort");
        algorithms.add("Merge Sort");

        algorithmPicker = new GuiDropdownList(0, 0, 180, 20, algorithms, e);
    }

    public void update(Main e, double delta) {
        double menuAnchorY = e.getScreenHeight() - e.menuHeight;

        resetButton.update(delta);
        setComponentPosition(resetButton, 120, menuAnchorY + 20);
        if (resetButton.isMousePressed()) {
            e.initializeSort();
        }

        togglePlaybackButton.update(delta);
        setComponentPosition(togglePlaybackButton, 20, menuAnchorY + 20);
        togglePlaybackButton.setText(e.executing ? "Pause" : "Play");
        if (togglePlaybackButton.isMousePressed()) {
            e.executing = !e.executing;
        }

        playbackScrubber.update(delta);
        playbackScrubber.setMax(e.sequence.size() - 1);
        setComponentPosition(playbackScrubber, 220, menuAnchorY + 20);
        // TODO: Existe uma forma melhor de validar que o Slider está sendo utilizado?
        if (!playbackScrubber.isMouseDown()) {
            playbackScrubber.setValue(e.index);
        } else {
            e.index = (int)playbackScrubber.getValue();
        }

        algorithmPicker.update(delta);
        setComponentPosition(algorithmPicker, 20, menuAnchorY + 50);
        currentSorter = algorithmPicker.getSelectedItemText();

        elementCountSpinnerLabel.update(delta);
        setComponentPosition(elementCountSpinnerLabel, 220, menuAnchorY + 50);
        elementCountSpinner.update(delta);
        setComponentPosition(elementCountSpinner, 300, menuAnchorY + 50);
        e.elementCount = elementCountSpinner.getValue();
    }
    public void draw(Main e) {
        resetButton.draw();
        togglePlaybackButton.draw();
        playbackScrubber.draw();
        algorithmPicker.draw();

        elementCountSpinnerLabel.draw();
        elementCountSpinner.draw();
    }

    private void setComponentPosition(GuiComponent component, double x, double y) {
        // Os componentes parecem não ter uma forma de definir a posição absoluta?
        // Tudo bem.
        component.move(
                x - component.getX(),
                y - component.getY()
        );
    }
}
