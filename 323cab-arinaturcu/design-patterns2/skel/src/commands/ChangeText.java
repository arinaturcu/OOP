package commands;

import diagram.DiagramCanvas;
import diagram.DiagramComponent;

public class ChangeText implements DrawCommand {
    private DiagramCanvas diagramCanvas;
    private int componentIndex;
    private String newText;
    private String previousText;

    public ChangeText(DiagramCanvas diagramCanvas, int componentIndex, String newText) {
        this.diagramCanvas = diagramCanvas;
        this.componentIndex = componentIndex;
        this.newText = newText;
    }

    @Override
    public void execute() {
        DiagramComponent component = diagramCanvas.getComponent(componentIndex);
        previousText = component.getText();
        component.setText(newText);
    }

    @Override
    public void undo() {
        DiagramComponent component = diagramCanvas.getComponent(componentIndex);
        component.setText(previousText);
    }

    @Override
    public String toString() {
        return "ChangeText{" +
                "diagramCanvas=" + diagramCanvas +
                ", componentIndex=" + componentIndex +
                ", newText='" + newText + '\'' +
                '}';
    }
}
