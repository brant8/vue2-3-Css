import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import com.example.pojo.People;

public class MyTest {

    @Test
    public void test1(){
        ApplicationContext context = new ClassPathXmlApplicationContext("context.xml");

        People people = context.getBean("people", People.class);
        people.getDog().bark();
        people.getCat().bark();


    }

}
