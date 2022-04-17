package Stream;

public class ArrayDemo {
    public static int[] createArray(int length,ArrayBuilder ab){
        return ab.builderArray(length);
    }

    public static void main(String[] args) {
        int[] arr1 = createArray(3,(len)->{
            return new int[len];
        });
        System.out.println(arr1.length);

        int[] arr2=createArray(10,int[]::new);
        System.out.println(arr2.length);
    }
}
