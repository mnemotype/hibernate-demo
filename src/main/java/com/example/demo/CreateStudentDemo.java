package com.example.demo;

import com.example.demo.hibernate.entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class CreateStudentDemo {
  
    public static void main(String[] args) {
        
        // create session factory
        SessionFactory factory = new Configuration()
                                .configure()
                                .addAnnotatedClass(Student.class)
                                .buildSessionFactory();
        // create session
        Session session = factory.getCurrentSession();
        
        try {
            int studentId = 1;
            
            session = factory.getCurrentSession();
            session.beginTransaction();
            
            System.out.println("\nGetting student with id: " + studentId + "\n");
            
            Student myStudent = session.get(Student.class, studentId);
            
            System.out.println("\nUpdating student....\n");
            myStudent.setFirstName("Scooby");
            
            // commit the transaction
            session.getTransaction().commit();
            
            
            // NEW CODE
            
            session = factory.getCurrentSession();
            session.beginTransaction();
            
            System.out.println("\n\nUpdate email for all students");
            
            session.createQuery("update Student set email='foo@gmail.com'").executeUpdate();
            
            // commit the transaction
            session.getTransaction().commit();        
            
            System.out.println("\nDone!\n");
            
        } finally {
            factory.close();
        }
        
    }
    
}