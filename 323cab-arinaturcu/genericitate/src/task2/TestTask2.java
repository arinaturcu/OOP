package task2;

import java.util.*;

public class TestTask2 {
    public static void main(String[] args) {
        Collection<MyMatrix> matrices = new LinkedList<>();
        Collection<MyVector3> arrays  = new LinkedList<>();

        matrices.add(new MyMatrix(new int[][]
                {{1, 1, 1, 1},
                        {1, 1, 1, 1},
                        {1, 1, 1, 1},
                        {1, 1, 1, 1}}
        ));
        matrices.add(new MyMatrix(new int[][]
                {{2, 2, 2, 2},
                        {2, 2, 2, 2},
                        {2, 2, 2, 2},
                        {2, 2, 2, 2}}
        ));
        matrices.add(new MyMatrix(new int[][]
                {{3, 3, 3, 3},
                        {3, 3, 3, 3},
                        {3, 3, 3, 3},
                        {3, 3, 3, 3}}
        ));

        arrays.add(new MyVector3(new int[] {1, 4, 7}));
        arrays.add(new MyVector3(new int[] {2, 5, 8}));
        arrays.add(new MyVector3(new int[] {3, 6, 9}));

        System.out.println(addCollection(arrays));
        System.out.println(addCollection(matrices));
    }

    public static <T extends Sumabil> T addCollection(Collection<T> collection) {
        Iterator<T> it = collection.iterator();
        ArrayList<T> result = new ArrayList<>(Collections.singletonList(it.next()));

        while (it.hasNext()){
            result.get(0).addValue(it.next());
        }

        return result.get(0);
    }
}