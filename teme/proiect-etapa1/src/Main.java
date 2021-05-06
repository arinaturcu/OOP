import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import input.Input;
import simulator.CurrentState;
import simulator.Simulator;

import java.io.File;

public final class Main {
    private Main() {

    }

    /**
     * Reads the input, runs the simulation and writes the result in the output file
     * @param args names of input and output files
     * @throws Exception exception from readValue()
     */
    public static void main(final String[] args) throws Exception {
        String inFileName  = args[0];
        String outFileName = args[1];

        File inFile  = new File(inFileName);
        File outFile = new File(outFileName);

        /* read input */
        ObjectMapper mapper = new ObjectMapper();
        Input input = mapper.readValue(inFile, Input.class);

        new Simulator().run(input);
        CurrentState currentState = CurrentState.getInstance();

        ObjectWriter writer = mapper.writer(new DefaultPrettyPrinter());
        writer.writeValue(outFile, currentState);

        CurrentState.reset();
    }
}
