package com.luv2code.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Course;
import com.luv2code.hibernate.demo.entity.Instructor;
import com.luv2code.hibernate.demo.entity.InstructorDetail;

public class EagerLazyDemo {

	public static void main(String[] args) {

		// create sessionFactory
		SessionFactory factory = new Configuration()
				// .configure("hibernate.cfg.xml")
				.configure()
				.addAnnotatedClass(Instructor.class)
				.addAnnotatedClass(InstructorDetail.class)
				.addAnnotatedClass(Course.class)
				.buildSessionFactory();

		// create session
		Session session = factory.getCurrentSession();

		try {
			// start a transaction
			session.beginTransaction();

			//get the instructor from db
			int theId = 1;
			Instructor tempInstructor = session.get(Instructor.class, theId);
			
			System.out.println("luv2code: Instructor : "+tempInstructor);
			
			System.out.println("luv2code: Courses : "+tempInstructor.getCourses());
			// commit the transaction
			session.getTransaction().commit();
			
			//close the session
			session.close();
			
			
			//option 1: call the getter method while session is open
			
			//get course for the instructor
			System.out.println("luv2code: Courses : "+tempInstructor.getCourses());
			
			// commit the transaction
			//session.getTransaction().commit();

			System.out.println("luv2code: Done!!!");

		} finally {
			
			//add cleanup code
			session.close();
			
			// Close the SessionFactory object
			factory.close();
		}

	}

}
