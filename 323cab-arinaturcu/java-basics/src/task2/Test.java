package task2;

public class Test {
    public static void main(String[] args) {
        Course c = new Course();
        Student[] students = new Student[3];

        for (int i = 0; i < 3; ++i) {
            students[i] = new Student();
        }

        students[0].setName("Ana");
        students[0].setYear(2);

        students[1].setName("Radu");
        students[1].setYear(1);

        students[2].setName("Ioana");
        students[2].setYear(2);

        c.setTitle("Object-Oriented Programming");
        c.setDescription("Description");
        c.setStudents(students);

        // Afiseaza studentii din anul dat ca parametru
        System.out.println(c.filterYear(Integer.parseInt(args[0])));

        Student student1 = new Student();
        Student student2 = new Student();

        // Verifica egalitatea
        student1.setYear(1);
        student2.setYear(1);
        student1.setName("Maria");
        student2.setName("Maria");

        // Va fi false pentru ca student1 si student2 nu arata spre acelasi obiect
        System.out.println("Check equality: " + student1.equals(student2));
    }
}
