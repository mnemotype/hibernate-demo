package com.example.demo;

import com.example.demo.hibernate.entity.Student;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class QueryStudentDemo {
  
    public static void main(String[] args) {
        
        // create session factory
        SessionFactory factory = new Configuration()
                                .configure()
                                .addAnnotatedClass(Student.class)
                                .buildSessionFactory();
        // create session
        Session session = factory.getCurrentSession();
        
        try {            
            // start a transaction
            session.beginTransaction();
            
            //  query students
            List<Student> theStudents = session.createQuery("from Student").getResultList();
            
            displayStudents(theStudents);
            
            // query students: lastName='Doe'
            theStudents = session.createQuery("from Student s where s.lastName='Doe'").getResultList();
            
            // display query
            System.out.println("\n\nStudents who have last name of Doe:");
            displayStudents(theStudents);
            
            // query and display students: lastName='Doe' OR firstName='Daffy'
            theStudents=session.createQuery("from Student s where s.lastName='Doe' OR s.firstName='Daffy'").getResultList();
            
            System.out.println("\n\nStudents who have lastName='Doe' OR firstName='Daffy'");
            displayStudents(theStudents);
            
            // query and display students who have e-mail on domain luv2code.com
            theStudents = session.createQuery("from Student s where s.email LIKE '%luv2code.com'").getResultList();
            
            System.out.println("\n\nStudents who have e-mail on domain luv2code.com");
            displayStudents(theStudents);
            
            // commit the transaction
            session.getTransaction().commit();
            System.out.println("\nDone!\n");
            
        } finally {
            factory.close();
        }
        
    }

    private static void displayStudents(List<Student> theStudents) {
        // display students
        for (Student tempStudent : theStudents)
        {
            System.out.println("\n" + tempStudent);
        }
        System.out.println("");
    }
    
}
