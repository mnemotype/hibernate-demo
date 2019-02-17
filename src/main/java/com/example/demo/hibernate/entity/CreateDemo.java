package com.example.demo.hibernate.entity;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import com.example.demo.onetomany.Instructor;

public class CreateDemo {
   
    public static void main(String[] args) {
       
        SessionFactory factory = new Configuration()
                                .configure("hibernate.cfg.xml")
                                .addAnnotatedClass(Instructor.class)
                                .addAnnotatedClass(InstructorDetail.class)
                                .buildSessionFactory();
        
        Session session = factory.getCurrentSession();
        
        try
        {
//             create objects
            Instructor tempInstructor = new Instructor("Chad", "Darby", "darby@luv2code.com");
            InstructorDetail tempInstructorDetail = new InstructorDetail("http://www.luv2code.com/youtube", "Luv 2 code!!!");
            
            Instructor tempInstructor1 = new Instructor("Madhu", "Patel", "madhu@luv2code.com");
            InstructorDetail tempInstructorDetail1 = new InstructorDetail("http://youtube.com/dupa", "Kocha programować!!!");
            
            Instructor tempInstructor2 = new Instructor("Krzysiek", "Bongowicz", "kris@luv2code.com");
            InstructorDetail tempInstructorDetail2 = new InstructorDetail("http://www.youtube.com/krzysio", "Dziwki bonga lasery!!!");
            
            Instructor tempInstructor3 = new Instructor("Zenek", "Martyniuk", "zenek@luv2code.com");
            InstructorDetail tempInstructorDetail3 = new InstructorDetail("http://www.youtube.com/ZenekMartyniuk", "Disco polo!!!");
            
            
            
            // utworzyć relację między obiektami
            tempInstructor.setInstructorDetail(tempInstructorDetail);
            tempInstructor1.setInstructorDetail(tempInstructorDetail1);
            tempInstructor2.setInstructorDetail(tempInstructorDetail2);
            tempInstructor3.setInstructorDetail(tempInstructorDetail3);
            

            // start a transaction
            session.beginTransaction();
            
//            int theId = 2;
//            InstructorDetail tempInstructorDetail = 
//                    session.get(InstructorDetail.class, theId);
//            
//            System.out.println("\n\ntempInstructorDetails: " + tempInstructorDetail);
//            System.out.println("Associated instructor: " + tempInstructorDetail.getInstructor());
//            System.out.println("\n\n");
            
            
            
            
            
            // save the instructor (this will ALSO save the details object because of CascateType.ALL)
            //System.out.println("\n\nSaving instructor: " + tempInstructor + "...\n\n\n");
            System.out.println("\n\nSaving instructors:");
            System.out.println(tempInstructor);
            System.out.println(tempInstructor1);
            System.out.println(tempInstructor2);
            System.out.println(tempInstructor3 + "\n\n");
            
            session.save(tempInstructor);
            session.save(tempInstructor1);
            session.save(tempInstructor2);
            session.save(tempInstructor3);
//            
            
                        
            // commit transaction
            session.getTransaction().commit();
            System.out.println("\n\nDone!\n\n");
            
            
        } catch (Exception ex)
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
