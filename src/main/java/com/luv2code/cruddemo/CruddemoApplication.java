package com.luv2code.cruddemo;

import com.luv2code.cruddemo.dao.AppDAO;
import com.luv2code.cruddemo.entity.*;
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
		return (runner) -> {
						//createCourseAndStudents(appDAO);
						//findCourseAndStudents(appDAO);
			            // findStudentAndCourses(appDAO);
						//addMoreCoursesForStudent(appDAO);
						//deleteCourse(appDAO);
						deleteStudent(appDAO);

		};
	}

	private void deleteStudent(AppDAO appDAO) {
		int id = 1;
		System.out.println("Deleting Student id: " + id);
		appDAO.deleteStudentById(id);
		System.out.println("Done!");

	}

	private void addMoreCoursesForStudent(AppDAO appDAO) {
		int id = 1;
		//fetch the student
		System.out.println("Getting the Student with id: " + id);
		Student student = appDAO.findStudentAndCoursesByStudentId(id);
		//get the associated courses
		List<Course> courses = student.getCourses();
		//create new courses
		Course course1 = new Course("Data Structure Course");
		Course course2 = new Course("Operating System Course");
		//add courses to the course list
		courses.add(course1);
		courses.add(course2);
		//associate courses and student
		student.setCourses(courses);
		//update the student in db
		appDAO.updateStudent(student);

	}

	private void findStudentAndCourses(AppDAO appDAO) {
		int id = 1;
		Student student = appDAO.findStudentAndCoursesByStudentId(id);
		System.out.println("Loaded Student: " + student);
		System.out.println("Associated Courses: " + student.getCourses());
		System.out.println("Done!");
	}

	private void findCourseAndStudents(AppDAO appDAO) {
		int id = 10;
		Course course = appDAO.findCourseAndStudentsByCourseId(id);
		System.out.println("Loaded Course: " + course);
		System.out.println("Associated Students: " + course.getStudents());
		System.out.println("Done!");

	}

	private void createCourseAndStudents(AppDAO appDAO) {
		//create a course
		Course course = new Course("Pacman - How to Score one million");

		//create the students
		Student student1 = new Student("John","Doe","john.luv2code.com");
		Student student2 = new Student("Mary","Public","mary.luv2code.com");

		//add students to the course
		course.addStudent(student1);
		course.addStudent(student2);

		//save the course and associated students
		System.out.println("Saving the course: " + course);
		System.out.println("Associated Students: " + course.getStudents());

		appDAO.saveCourse(course);
		System.out.println("Done!");
	}

	private void deleteCourseAndReviews(AppDAO appDAO) {
		int id = 11;
		System.out.println("Deleting course id: " + id);
		appDAO.deleteCourseById(id);
		System.out.println("Done!");
	}

	private void retrieveCourseAndReviews(AppDAO appDAO) {
		int id = 11;
		System.out.println("Finding Course id: " + id);
		Course course = appDAO.findCourseAndReviewsByCourseId(id);
		//print the course
		System.out.println(course);
		//print the reviews
		System.out.println(course.getReviews());
		System.out.println("Done!");
	}

	private void createCourseAndReviews(AppDAO appDAO) {
		//create a course
		Course course = new Course("Pacman - How to score one million points");
		//add reviews
		course.addReview(new Review("Great Course ... Loved it!"));
		course.addReview(new Review("Cool course, job well done"));
		course.addReview(new Review("What a dumb course, you are an idiot"));

		//save the course and leverage the cascade all
		System.out.println("Saving the course");
		System.out.println(course);
		System.out.println(course.getReviews());

		appDAO.saveCourse(course);

		System.out.println("Done!");
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
