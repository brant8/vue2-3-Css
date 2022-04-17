package Stream;

public class Man extends Human{
    @Override
    public void sayHello() {
        System.out.println("Hello, I am a man.");
    }

    public void method(Greetable g){
        g.greet();
    }
    public void showMe(){
//        method(()->{
////            Man m = new Man();
////            m.sayHello();
//            this.sayHello();
//        });
        method(this::sayHello);
    }
    public void show(){
        method(()->{
            Human h = new Human();
            h.sayHello();
        });
    }

    public static void main(String[] args) {
        new Man().showMe();

    }
}
