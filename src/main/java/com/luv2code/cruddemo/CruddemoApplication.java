package com.luv2code.cruddemo;

import com.luv2code.cruddemo.dao.AppDAO;
import com.luv2code.cruddemo.entity.Course;
import com.luv2code.cruddemo.entity.Instructor;
import com.luv2code.cruddemo.entity.InstructorDetail;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.List;


@SpringBootApplication
public class CruddemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(CruddemoApplication.class, args);

	}

	@Bean
	public CommandLineRunner commandLineRunner(AppDAO appDAO) {
		return (abc) -> {
			//createInstructor(appDAO);
			//findInstructor(appDAO);
			//deleteInstructor(appDAO);
			//findInstructorDetail(appDAO);
			//deleteInstructorDetail(appDAO);
			//createInstructorWithCourses(appDAO);
			//findInstructorWithCourses(appDAO);
			//findCoursesForInstructor(appDAO);
			//findInstructorWithCoursesJoinFetch(appDAO);
			//updateInstructor(appDAO);
			//updateCourse(appDAO);
			//deleteInstructor(appDAO);
			deleteCourse(appDAO);


		};
	}

	private void deleteCourse(AppDAO appDAO) {
		int id = 10;
		System.out.println("Deleting Course id: " + id);
		appDAO.deleteCourseById(id);
		System.out.println("Done!");

	}

	private void updateCourse(AppDAO appDAO) {
		int id = 10;

		//Find the course
		System.out.println("Finding Course id: " + id);
		Course course = appDAO.findCourseById(id);

		//update the course
		System.out.println("Updating Course id: " + id);
		course.setTitle("Enjoy the Simple Things");

		appDAO.update(course);

		System.out.println("Done!");
	}

	private void updateInstructor(AppDAO appDAO) {
		int id = 1;
		System.out.println("Finding Instructor Id: " + id);
		Instructor instructor = appDAO.findInstructorById(id);
		System.out.println("Updating Instructor Id: " + id);
		instructor.setLastName("TESTER");
		appDAO.update(instructor);
		System.out.println("Done!");

	}

	private void findInstructorWithCoursesJoinFetch(AppDAO appDAO) {
		int id = 1;
		System.out.println("Finding instructor id: " + id);
		Instructor instructor = appDAO.findInstructorByIdJoinFetch(id);
		System.out.println("Instructor: " + instructor);
		System.out.println("The associated courses: " + instructor.getCourses());
		System.out.println("Done!");
	}

	private void findCoursesForInstructor(AppDAO appDAO) {
		int id = 1;
		System.out.println("Finding instructor id: " + id);
		Instructor instructor = appDAO.findInstructorById(id);
		System.out.println("Instructor: " + instructor);
		//find courses for instructor
		System.out.println("Finding courses for instructor id: " + id);
		List<Course> courses = appDAO.findCoursesByInstructorId(id);
		//associate the objects
		instructor.setCourses(courses);
		System.out.println("The associated courses: " + instructor.getCourses());
		System.out.println("Done!");

	}

	private void findInstructorWithCourses(AppDAO appDAO) {
		int id = 3;
		System.out.println("Finding instructor id: " + id);
		Instructor instructor = appDAO.findInstructorById(id);
		System.out.println("Instructor: " + instructor);
		instructor.getCourses().size();
		System.out.println("The associated courses: " + instructor.getCourses());
		System.out.println("Done!");

	}

	private void createInstructorWithCourses(AppDAO appDAO) {
		// create the instructor
		Instructor instructor =
				new Instructor("Susan", "Public", "susan.public@luv2code.com");
		//create the instructor detail
		InstructorDetail instructorDetail =
				new InstructorDetail("http://www.youtube.com", "Video Games");
		//associate the objects
		instructor.setInstructorDetail(instructorDetail);
		//create some courses
		Course course1 = new Course("Air Guitar - The Ultimate Guide2");
		//link the course to instructor
		course1.setInstructor(instructor);
		Course course2 = new Course("The Pinball Masterclass2");
		//link the course to instructor
		course2.setInstructor(instructor);
		List<Course> courses = new ArrayList<>();
		courses.add(course1);
		courses.add(course2);
		//add courses to instructor
		instructor.setCourses(courses);
//		instructor.add(course1);
//		instructor.add(course2);
		//save the instructor
		//
		//NOTE: This will also save the courses
		//because of CascadeType.PERSIST
		System.out.println("Saving Instructor: " + instructor);
		System.out.println("The courses: " + instructor.getCourses());
		appDAO.save(instructor);
		System.out.println("Done!");

	}

	private void deleteInstructorDetail(AppDAO appDAO) {
		int id = 5;
		System.out.println("Deleting  Instructor detail id: " + id);
		appDAO.deleteInstructorDetailById(id);
		System.out.println("Done!");
	}

	private void findInstructorDetail(AppDAO appDAO) {
		int id = 2;
		System.out.println("Finding instructor detail id: " + id);

		InstructorDetail instructorDetail = appDAO.findInstructorDetailById(id);

		System.out.println("Instructor Detail: " + instructorDetail);
		System.out.println("the associated instructor only: " + instructorDetail.getInstructor());
	}

	private void deleteInstructor(AppDAO appDAO) {
		int id = 1;
		System.out.println("Deleting  Instructor id: " + id);
		appDAO.deleteInstructorById(id);
		System.out.println("Done!");
	}

	private void findInstructor(AppDAO appDAO) {
		int id = 3;
		System.out.println("Finding instructor id: " + id);

		Instructor instructor = appDAO.findInstructorById(id);

		System.out.println("Instructor: " + instructor);
		System.out.println("the associated instructorDetail only: " + instructor.getInstructorDetail());
	}

	private void createInstructor(AppDAO appDAO) {
		// create the instructor
		/*Instructor instructor =
				new Instructor("Chad","Darby","darby@luv2code.com");
		//create the instructor detail
		InstructorDetail instructorDetail =
				new InstructorDetail("http://www.luv2code.com/youtube","Luv 2 Code!!!");
		//associate the objects
		instructor.setInstructorDetail(instructorDetail);*/

		// create the instructor
		Instructor instructor =
				new Instructor("Keshav", "Kumar", "keshav@luv2code.com");
		//create the instructor detail
		InstructorDetail instructorDetail =
				new InstructorDetail("http://www.luv2code.com/youtube", "coding!!!");
		//associate the objects
		instructor.setInstructorDetail(instructorDetail);

		//save the instructor
		//NOTE: This will also save the instructor details object
		//because of CascadeType.ALL
		System.out.println("Saving instructor: " + instructor);
		appDAO.save(instructor);
		System.out.println("Done!");
	}

}
