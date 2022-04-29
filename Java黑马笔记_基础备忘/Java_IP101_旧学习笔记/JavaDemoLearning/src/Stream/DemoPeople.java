package Stream;

public class DemoPeople {
    public static void printName(String name,PeopleBuilder p){
        People people = p.buildPeople(name);
        System.out.println(people.getName());
    }
    public static void main(String[] args) {
        printName("DiLi",(String name)->{
            return new People(name);
        });
    }
}
