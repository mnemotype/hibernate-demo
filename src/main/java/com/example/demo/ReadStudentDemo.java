package com.example.demo;

import com.example.demo.hibernate.entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class ReadStudentDemo {
  
    public static void main(String[] args) {
        
        // create session factory
        SessionFactory factory = new Configuration()
                                .configure()
                                .addAnnotatedClass(Student.class)
                                .buildSessionFactory();
        // create session
        Session session = factory.getCurrentSession();
        
        try {
            // create a student object
            System.out.println("Creating a new student object...");
            Student tempStudent = new Student("Daffy", "Duck", "daffy@luv2code.com");
            
            // start a transaction
            session.beginTransaction();
            
            // save the student object
            System.out.println("Saving the student...");
            session.save(tempStudent);
            
            // commit the transaction
            session.getTransaction().commit();
            
            // MY NEW CODE
            
            // znaleźć primary key
            System.out.println("\n\nSaved student. Geredated id: " + tempStudent.getId() + "\n\n");
            
            // get a new session and start transaction
            session = factory.getCurrentSession();
            session.beginTransaction();
            
            // retrieve student based on the id: primary key
            System.out.println("\nGetting student with id: " + tempStudent.getId() + "\n");
            
            Student myStudent = session.get(Student.class, tempStudent.getId());
            
            System.out.println("\nGet complete: " + myStudent + "\n");
            // commit transaction
            session.getTransaction().commit();
            
            System.out.println("\nDone!\n");
            
        } finally {
            factory.close();
        }
        
    }
    
}
