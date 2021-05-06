import java.util.ArrayList;

public final class MyImmutableArray {
    private final ArrayList<Integer> immutableArray;

    public MyImmutableArray(ArrayList<Integer> array) {
        immutableArray = new ArrayList<>();
        immutableArray.addAll(array);
    }

    /**
     *
     * @return a copy of immutableArray
     */
    public ArrayList<Integer> getArray() {
        return new ArrayList<>(immutableArray);
    }
}
