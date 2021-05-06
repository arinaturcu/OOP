package commands;

import diagram.DiagramCanvas;
import diagram.DiagramComponent;

public class ChangeColor implements DrawCommand {
    private DiagramCanvas diagramCanvas;
    private int componentIndex;
    private String newColor;
    private String previousColor;

    public ChangeColor(DiagramCanvas diagramCanvas, int componentIndex, String newColor) {
        this.diagramCanvas = diagramCanvas;
        this.componentIndex = componentIndex;
        this.newColor = newColor;
    }

    @Override
    public void execute() {
        DiagramComponent component = diagramCanvas.getComponent(componentIndex);
        previousColor = component.getColor();
        component.setColor(newColor);
    }

    @Override
    public void undo() {
        DiagramComponent component = diagramCanvas.getComponent(componentIndex);
        component.setColor(previousColor);
    }

    @Override
    public String toString() {
        return "ChangeColor{" +
                "diagramCanvas=" + diagramCanvas +
                ", componentIndex=" + componentIndex +
                ", newColor='" + newColor + '\'' +
                '}';
    }
}
