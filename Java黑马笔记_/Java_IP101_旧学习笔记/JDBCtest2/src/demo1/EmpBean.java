package demo1;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmpBean {
    public static void main(String[] args) {
        List<Emp> list = new EmpBean().findAll();
        System.out.println(list);
    }
    public List<Emp> findAll(){
        Statement stmt = null;
        Connection conn= null;
        ResultSet rs = null;
        List<Emp> list = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
             conn =DriverManager.getConnection("jdbc:mysql:///java","root","");
            String sql = " select * from emp";
            stmt = conn.createStatement();
             rs = stmt.executeQuery(sql);
            Emp emp = null;
            list = new ArrayList<>();
            while(rs.next()){
                int id = rs.getInt("id");
                String name = rs.getString("name");
                int job_id = rs.getInt("job_id");
                //int mgr = rs.getInt("mgr");
                Date join_date = rs.getDate("joindate");
                String gender = rs.getString("gender");
                double salary = rs.getDouble("salary");
                //double bonus = rs.getDouble("bonus");
                int dept_id = rs.getInt("dept_id");

                emp=new Emp();
                emp.setId(id);
                emp.setName(name);
                emp.setJoin_date(join_date);
//                emp.setGender(gender);
                //emp.setJob_id(job_id);
                //emp.setMgr(mgr);
                emp.setSalary(salary);
                //emp.setBonus(bonus);
                emp.setDept_id(dept_id);

                list.add(emp);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if(rs!=null){
                try {
                    rs.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }            if(stmt!=null){
                try {
                    stmt.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }            if(conn!=null){
                try {
                    conn.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        }
        return list;
    }
}
