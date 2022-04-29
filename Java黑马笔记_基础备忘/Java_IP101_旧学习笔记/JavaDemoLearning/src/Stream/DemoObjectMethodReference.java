package Stream;

public class DemoObjectMethodReference {
    public static void printString(Printable printable){
        printable.print("Hello");
    }

    public static void main(String[] args) {
        printString(s->{
            MethodRerObject obj = new MethodRerObject();
            obj.printUpperCaseString(s);
        });

        MethodRerObject obj = new MethodRerObject();
        printString(new MethodRerObject()::printUpperCaseString);
        printString(obj::printUpperCaseString);
    }
}
