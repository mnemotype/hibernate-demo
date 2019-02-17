package com.example.demo.hibernate.entity;

import com.example.demo.onetomany.Instructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class DeleteDemo {
   
    public static void main(String[] args) {
       
        SessionFactory factory = new Configuration()
                                .configure("hibernate.cfg.xml")
                                .addAnnotatedClass(Instructor.class)
                                .addAnnotatedClass(InstructorDetail.class)
                                .buildSessionFactory();
        
        Session session = factory.getCurrentSession();
        
        try
        {
           
            // start a transaction
            session.beginTransaction();
           
            // get instructor by primary key / id
            int theId = 14;
            InstructorDetail tempInstructorDetail = session.get(InstructorDetail.class, theId);
            
            System.out.println("\n\nFound instructor's details: " + tempInstructorDetail);
            System.out.println("The associated instructor: " + tempInstructorDetail.getInstructor());
            System.out.println("\n\n");
                
            // delete the instructors 
            if (tempInstructorDetail != null)
            {
                System.out.println("\n\nDeleting: " + tempInstructorDetail + "\n\n");
                
                // remove the associated object reference
                // break bi-directional link
                tempInstructorDetail.getInstructor().setInstructorDetail(null);
                
                
                // Note: usunie również powiązany wiersz tabeli InstructorDetail
                // because of CascadeType.ALL
                //
                session.delete(tempInstructorDetail);
            }
            
            
                        
            // commit transaction
            session.getTransaction().commit();
            System.out.println("\n\nDone!\n\n");
            
            
        } finally {
            System.out.println("\n\nZamykam dupę...\n\n");
            factory.close();
            System.out.println("\nDupa zamknięta pomyślnie :-)\n");
        }
        
    }

}
