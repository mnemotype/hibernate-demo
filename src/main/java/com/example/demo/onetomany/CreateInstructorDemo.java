package com.example.demo.onetomany;

import com.example.demo.hibernate.entity.InstructorDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class CreateInstructorDemo {
   
    public static void main(String[] args) {
       
        SessionFactory factory = new Configuration()
                                .configure("hibernate.cfg.xml")
                                .addAnnotatedClass(Instructor.class)
                                .addAnnotatedClass(Course.class)
                                .addAnnotatedClass(InstructorDetail.class)
                                .buildSessionFactory();
        
        Session session = factory.getCurrentSession();
        
        try {
//            // get the instructor object
//            int theId = 1;
//            Instructor tempInstructor = session.get(Instructor.class, theId);
//            
//            // print instructor and associated courses
//            System.out.println("\n\ntempInstructor: " + tempInstructor);
//            System.out.println("courses: " + tempInstructor.getCourses());
//            System.out.println("\n\n");

                // create objects
                Instructor tempInstructor = 
                        new Instructor("Susan", "Patel", "susan@luv2code.com");
                
                InstructorDetail tempInstructorDetail = 
                        new InstructorDetail("http://www.youtube.com/susanna", "Singing");
            
                // powiąż obiekty
                tempInstructor.setInstructorDetail(tempInstructorDetail);
                
                // start a transaction
                session.beginTransaction();
                
                // save instructor
                System.out.println("\n\nSaving instructor: " + tempInstructor);
                session.save(tempInstructor);
                
                session.getTransaction().commit();
                System.out.println("\n\nDone!\n\n");
            
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
        finally {
            System.out.println("\n\nZamykam dupę...\n\n");
            
            // zamyka sesję w przypadku, gdyby w trakcie pracy wystąpił błąd i się nie zamknęła
            session.close();
            
            factory.close();
            System.out.println("\nDupa zamknięta pomyślnie :-)\n");
        }

        
    }

}
