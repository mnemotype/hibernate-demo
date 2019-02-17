package com.example.demo.onetomany;

import com.example.demo.hibernate.entity.InstructorDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class GetInstructorCoursesDemo {
   
    public static void main(String[] args) {
       
        SessionFactory factory = new Configuration()
                                .configure("hibernate.cfg.xml")
                                .addAnnotatedClass(Instructor.class)
                                .addAnnotatedClass(Course.class)
                                .addAnnotatedClass(InstructorDetail.class)
                                .buildSessionFactory();
        
        Session session = factory.getCurrentSession();
        
        try {       
                // start a transaction
                session.beginTransaction();
                
                // get the instructor from db
                int theId = 1;
                System.out.println("\n\nBefore getting the instructor\n\n");
                Instructor tempInstructor = session.get(Instructor.class, theId);
                System.out.println("\n\nAfter getting the instructor\n\n");
                
                System.out.println("\n\n");
                System.out.println("Instructor: " + tempInstructor);
                System.out.println("\n\n");
                
                // get course for the instructor
                System.out.println("\n\nCourses: " + tempInstructor.getCourses());
                System.out.println("\n\n");
                
            tempInstructor.toString();

                
                session.getTransaction().commit();
                System.out.println("\n\nDone!\n\n");
            
        } catch (Exception ex)
        {
            System.out.println("\n---------------------------------------\n\n");
            ex.printStackTrace();
            System.out.println("\n\n-------------------------------------\n\n");
        } finally {
            System.out.println("\n\nZamykam dupę...\n\n");
            
            // zamyka sesję w przypadku, gdyby w trakcie pracy wystąpił błąd i się nie zamknęła
            session.close();
            
            factory.close();
            System.out.println("\nDupa zamknięta pomyślnie :-)\n");
        }       
    }
}
