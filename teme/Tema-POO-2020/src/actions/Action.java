package actions;

import data.Data;
import fileio.ActionInputData;
import fileio.Writer;
import org.json.simple.JSONArray;
import java.io.IOException;

public abstract class Action {
    private final Data data;
    private final JSONArray arrayResult;
    private final Writer fileWriter;

    public Action(final Data data, final JSONArray arrayResult, final Writer fileWriter) {
        this.data        = data;
        this.arrayResult = arrayResult;
        this.fileWriter  = fileWriter;
    }

    /**
     * executes the action
     * @param action parsed action
     * @throws IOException exception
     */
    public abstract void execute(ActionInputData action) throws IOException;

    /**
     *
     * @return the input
     */
    public Data getData() {
        return data;
    }

    /**
     *
     * @return the JSONArray
     */
    public JSONArray getArrayResult() {
        return arrayResult;
    }

    /**
     *
     * @return the fileWriter
     */
    public Writer getFileWriter() {
        return fileWriter;
    }
}
