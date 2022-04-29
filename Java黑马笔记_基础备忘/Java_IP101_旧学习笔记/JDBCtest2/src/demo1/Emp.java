package demo1;

import java.util.Date;

public class Emp {
    private int id;
    private String name;
    private String gender;
    //private int job_id;
    //private int mgr;
    private double salary;
    //private double bonus;
    private int dept_id;
    private Date join_date;

    public Emp() {
    }

    public Emp(int id, String name, String gender, double salary, int dept_id, Date join_date) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.salary = salary;
        this.dept_id = dept_id;
        this.join_date = join_date;
    }

    @Override
    public String toString() {
        return "Emp{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", gender=" + gender +
                ", salary=" + salary +
                ", dept_id=" + dept_id +
                ", join_date=" + join_date +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

//    public char getGender() {
//        return gender;
//    }

//  //  public void setGender(char gender) {
//        this.gender = gender;
//    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public int getDept_id() {
        return dept_id;
    }

    public void setDept_id(int dept_id) {
        this.dept_id = dept_id;
    }

    public Date getJoin_date() {
        return join_date;
    }

    public void setJoin_date(Date join_date) {
        this.join_date = join_date;
    }
}
