package com.example.demo;

import com.example.demo.hibernate.entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class PrimaryKeyDemo {
   
    public static void main(String[] args) {
       
        // create session factory
        SessionFactory factory = new Configuration()
                                .configure()
                                .addAnnotatedClass(Student.class)
                                .buildSessionFactory();
        // create session
        Session session = factory.getCurrentSession();
        
        try {
            // create 3 student objects
            System.out.println("Creating a new student object...");
            Student tempStudent1 = new Student("Mary", "Public", "mary@luv2code.com");
            Student tempStudent2 = new Student("John", "Doe", "john@luv2code.com");
            Student tempStudent3 = new Student("Bonita", "Applebum", "bonita@luv2code.com");
            
            // start a transaction
            session.beginTransaction();
            
            // save the student objects
            System.out.println("Saving 3 students...");
            session.save(tempStudent1);
            session.save(tempStudent2);
            session.save(tempStudent3);
            
            // commit the transaction
            session.getTransaction().commit();
            System.out.println("Done!");
            
        } finally {
            factory.close();
        }
        
    }

}
