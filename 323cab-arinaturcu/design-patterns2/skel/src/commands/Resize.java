package commands;

import diagram.DiagramCanvas;
import diagram.DiagramComponent;

import java.awt.*;

public class Resize implements DrawCommand {
    private DiagramCanvas diagramCanvas;
    private int componentIndex;
    private double percentage;

    public Resize(DiagramCanvas diagramCanvas, int componentIndex, double percentage) {
        this.diagramCanvas = diagramCanvas;
        this.componentIndex = componentIndex;
        this.percentage = percentage;
    }

    @Override
    public void execute() {
        DiagramComponent component = diagramCanvas.getComponent(componentIndex);
        component.setHeight((int) (component.getHeight() * percentage));
        component.setWeight((int) (component.getWeight() * percentage));
    }

    @Override
    public void undo() {
        DiagramComponent component = diagramCanvas.getComponent(componentIndex);
        component.setHeight((int) (component.getHeight() * (1/percentage)));
        component.setWeight((int) (component.getWeight() * (1/percentage)));
    }

    @Override
    public String toString() {
        return "Resize{" +
                "diagramCanvas=" + diagramCanvas +
                ", componentIndex=" + componentIndex +
                ", percentage=" + percentage +
                '}';
    }
}
