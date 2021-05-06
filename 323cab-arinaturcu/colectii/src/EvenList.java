import java.util.HashSet;
import java.util.LinkedList;
import java.util.TreeSet;

/*
 * TreeSet se inlocuieste cu LinkedList sau HashSet
 * Concluziile sunt scrise in clasa Test
 */
public class EvenList extends TreeSet<Integer> {
    @Override
    public boolean add(Integer integer) {
        if (integer % 2 == 0) {
            return super.add(integer);
        } else {
            return false;
        }
    }

//    @Override
//    public void add(int index, Integer element) {
//        if (element % 2 == 0) {
//            super.add(index, element);
//        }
//    }
}
