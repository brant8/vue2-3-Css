package Stream;

public class People {
    private String name;

    public People() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "People{" +
                "name='" + name + '\'' +
                '}';
    }

    public People(String name) {
        this.name = name;
    }
}
