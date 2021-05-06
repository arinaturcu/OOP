import java.util.*;

public class Test {
    public static void main(String[] args) {
        List<Student> students = new ArrayList<>();
        students.add(new Student(1000, "Tanase",     "Ana",      9.56));
        students.add(new Student(1001, "Voiculescu", "Nicoleta", 9.31));
        students.add(new Student(1002, "Popescu",    "Andreea",  8.82));
        students.add(new Student(1003, "Voiculescu", "Andrei",   9.31));
        students.add(new Student(1004, "Popa",       "Andreea",  8.82));

        /* Sortare prin implementarea interfetei Comparable */
        Collections.sort(students);

//        /* Sortare cu List.sort dupa averageGrade*/
//        students.sort((o1, o2) -> ((o1.getAverageGrade() - o2.getAverageGrade()) > 0 ? -1 :
//                                   (o1.getAverageGrade() - o2.getAverageGrade() < 0 ? 1 : 0)));
//
//        /* Sortare cu Collections.sort dupa averageGrade*/
//        Collections.sort(students, (o1, o2) -> ((o1.getAverageGrade() - o2.getAverageGrade()) > 0 ? -1 :
//                                               (o1.getAverageGrade() - o2.getAverageGrade() < 0 ? 1 : 0)));

        System.out.println("List de studenti sortata:\n" + students + "\n");

        PriorityQueue<Student> priorityQueue = new PriorityQueue<>(5, new Comparator<Student>() {
            @Override
            public int compare(Student o1, Student o2) {
                return Long.compare(o1.getId(), o2.getId());
            }
        });

        priorityQueue.addAll(students);
        System.out.println("Elementul din capatul cozii:\n" + priorityQueue.peek() + "\n");

        HashMap<Student, List<String>> studentAndCourses = new HashMap<>();
        studentAndCourses.put(students.get(0), new ArrayList<>(Arrays.asList("POO", "AA", "TS")));
        studentAndCourses.put(students.get(1), new ArrayList<>(Arrays.asList("AA", "TS")));
        studentAndCourses.put(students.get(2), new ArrayList<>(Arrays.asList("IOCLA", "POO", "AA", "TS")));
        studentAndCourses.put(students.get(3), new ArrayList<>(Arrays.asList("POO", "USO", "TS")));
        studentAndCourses.put(students.get(4), new ArrayList<>(Arrays.asList("POO", "AA", "PC")));

        System.out.println("HashMap:");
        Set<Map.Entry<Student, List<String>>> s = studentAndCourses.entrySet();
        for (Map.Entry<Student, List<String>> it : s) {
            System.out.println(it.getKey() + ": " + it.getValue());
        }

        EvenList list = new EvenList();
        list.add(1);
        list.add(6);
        list.add(2);
        list.add(3);
        list.add(5);
        list.add(4);

        /*
         * Daca EvenList extinde LinkedList, elementele vor ramane in ordinea in care
         * au fost inserate.
         * Daca EvenList extinde HashSet, elementele vor fi pastrate in
         * functie de hash-ul fiecaruia dar, in acest caz in care elementele sunt de tip
         * int, hash-ul lor va fi egal cu ele (i.e. elementul 1 va avea hash-ul 1), deci
         * elementele vor fi pastrate in ordine crescatoare.
         * Daca EvenList extinde TreeSet, elementele sunt pastrate in ordinea lor naturala
         * pentru ca asta face TreeSet daca nu ii este dat un Comparator sau Comparable.
         */
        System.out.println("\nLista de numere pare:\n" + list);
    }
}
