package Stream;

public class DemoPrintable {
    public static void printString(Printable p){
        p.print("Hello World");
    }

    public static void main(String[] args) {
        printString((s->{
            System.out.println(s);
        }));

        printString(System.out::println);
    }
}
