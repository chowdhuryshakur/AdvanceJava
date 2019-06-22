/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javadaodemo;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Shakur
 */
public class StudentDAOImplForSQL implements StudentDataAccessObject {

    Connection connection = DatabaseConnection.getConnection();
   
    @Override
    public Student insert(Student student) {        
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate("insert into student values('"+student.getId()+"','"+student.getName()+"','"+student.getMail()+"');");
        } catch (SQLException ex) {
            Logger.getLogger(StudentDAOImplForSQL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public Student getStduentbyID(String Id) {
        Student student = null;       
        try {
            Statement statement = connection.createStatement();
            ResultSet resultset = statement.executeQuery("select * from student where id = '"+Id+"';");
            while(resultset.next()){
                String studentId = resultset.getString("id");
                String name = resultset.getString("name");
                String email = resultset.getString("email");
                
                student = new Student(studentId, name, email);
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(StudentDAOImplForSQL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return student;
       
        }

    @Override
    public List<Student> getAllStudent() {
        List<Student>studentList = new ArrayList <>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultset = statement.executeQuery("select * from student;");
            while(resultset.next()){
                String studentId = resultset.getString("id");
                String name = resultset.getString("name");
                String email = resultset.getString("email");
                
                Student student = new Student(studentId, name, email);
                studentList.add(student);
            }
        } catch (SQLException ex) {
            Logger.getLogger(StudentDAOImplForSQL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return studentList;
    }

    @Override
    public Student update(String Id, Student student) {
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate("update student set name = '"+student.getName()+"', email = '"+student.getMail()+"' where id = '"+Id+"';");
        } catch (SQLException ex) {
            Logger.getLogger(StudentDAOImplForSQL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public void delete(String Id) {
       try {
            Statement statement = connection.createStatement();
            statement.executeUpdate("delete from student where id = '"+Id+"';");
        } catch (SQLException ex) {
            Logger.getLogger(StudentDAOImplForSQL.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
