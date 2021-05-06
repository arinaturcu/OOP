import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        // Test PasswordMaker
        System.out.println("Test PasswordMaker");
        PasswordMaker pm = new PasswordMaker("arina");
        System.out.println(pm.getPassword());

        // Test PasswordMakerSingleton
        System.out.println("\nTest PasswordMakerSingleton");
        PasswordMakerSingleton pms = PasswordMakerSingleton.getInstance();
        System.out.println(pms.getPassword());

        // Test PasswordMakerSingletonMod
        System.out.println("\nTest PasswordMakerSingletonMon");
        PasswordMakerSingletonMod pmsm = PasswordMakerSingletonMod.getInstance();
        PasswordMakerSingletonMod pmsm1 = PasswordMakerSingletonMod.getInstance();
        PasswordMakerSingletonMod pmsm2 = PasswordMakerSingletonMod.getInstance();

        System.out.println("Number of calls for getInstance(): " + PasswordMakerSingletonMod.getCount());

        // Test MyImmutableArray
        System.out.println("\nTest MyImmutableArray");
        ArrayList<Integer> array = new ArrayList<>();
        int[] a = {1, 2, 3, 4, 5};
        for (int i : a) {
            array.add(i);
        }

        MyImmutableArray immutableArray = new MyImmutableArray(array);
        array = immutableArray.getArray();
        System.out.println("immutableArray before trying to change it: " + array);
        array.add(12);
        System.out.println("immutableArray after trying to change it: " + immutableArray.getArray());
        System.out.println("The ArrayList is unchanged");
    }
}
