package commands;

import diagram.DiagramCanvas;
import diagram.DiagramComponent;

public class ConnectComponents implements DrawCommand {
    private DiagramCanvas diagramCanvas;
    private int component1Index;
    private int component2Index;

    public ConnectComponents(DiagramCanvas diagramCanvas, int component1Index, int component2Index) {
        this.diagramCanvas = diagramCanvas;
        this.component1Index = component1Index;
        this.component2Index = component2Index;
    }

    @Override
    public void execute() {
        DiagramComponent component1 = diagramCanvas.getComponent(component1Index);
        DiagramComponent component2 = diagramCanvas.getComponent(component2Index);

        component1.connectTo(Integer.toString(component1Index));
        component2.connectTo(Integer.toString(component2Index));
    }

    @Override
    public void undo() {
        DiagramComponent component1 = diagramCanvas.getComponent(component1Index);
        DiagramComponent component2 = diagramCanvas.getComponent(component2Index);

        component1.removeConnection(Integer.toString(component1Index));
        component2.removeConnection(Integer.toString(component2Index));
    }

    @Override
    public String toString() {
        return "ConnectComponents{" +
                "diagramCanvas=" + diagramCanvas +
                ", component1Index=" + component1Index +
                ", component2Index=" + component2Index +
                '}';
    }
}
