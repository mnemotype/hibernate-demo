package com.example.demo;

import com.example.demo.hibernate.entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class DeleteStudentDemo {
  
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
            
            int studentId = 2;
            
            //System.out.println("\n\nGetting student with id: " + studentId);
            //Student myStudent = session.get(Student.class, studentId);
            
            // delete the student
            // session.delete(myStudent);
            // System.out.println("\n\n Student with id=" + studentId + " deleted succesfully.\n\n");
            
            // delete the student 
            System.out.println("\n\nDeleting student with id=1...\n\n");
            session.createQuery("delete from Student where id=1").executeUpdate();
            
            
            // commit transaction
            session.getTransaction().commit();
            
            System.out.println("\nDone!\n");
            
        } finally {
            factory.close();
        }
        
    }
    
}
