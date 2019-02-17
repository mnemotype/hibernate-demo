package com.example.demo.onetomany;

import com.example.demo.hibernate.entity.InstructorDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class CreateCoursesDemo {
   
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
                Instructor tempInstructor = session.get(Instructor.class, theId);
                                
                // create some courses
                Course tempCourse1 = new Course("Air Guitar - The Ultimate Guide");
                Course tempCourse2 = new Course("The Paintball Masterclass");
                
                // add courses to instructor
                tempInstructor.add(tempCourse1);
                tempInstructor.add(tempCourse2);
                
                // save the courses
                session.save(tempCourse1);
                session.save(tempCourse2);
             
                
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
