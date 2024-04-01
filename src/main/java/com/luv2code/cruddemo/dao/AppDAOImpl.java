package com.luv2code.cruddemo.dao;

import com.luv2code.cruddemo.entity.Course;
import com.luv2code.cruddemo.entity.Instructor;
import com.luv2code.cruddemo.entity.InstructorDetail;
import com.luv2code.cruddemo.entity.Student;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class AppDAOImpl implements AppDAO{
    private final EntityManager entityManager;
    //constructor injection
    @Autowired
    public AppDAOImpl(EntityManager entityManager){
        this.entityManager = entityManager;
    }
    @Override
    @Transactional
    public void save(Instructor instructor) {
        entityManager.persist(instructor);
    }

    @Override
    public Instructor findInstructorById(int id) {
        return entityManager.find(Instructor.class,id);
    }

    @Override
    @Transactional
    public void deleteInstructorById(int id) {
        //fetch instructor by id
        Instructor instructor = entityManager.find(Instructor.class, id);
        //get associated courses
        List<Course> courses = instructor.getCourses();
//        //break the association of all courses with associated instructor
//        for (Course course:courses) {
//            course.setInstructor(null);
//        }
        //Streams code
        courses.forEach(course->course.setInstructor(null));

        //delete the fetched instructor
        entityManager.remove(instructor);
    }
    @Override
    public InstructorDetail findInstructorDetailById(int id) {
        return entityManager.find(InstructorDetail.class,id);
    }
    @Transactional
    @Override
    public void deleteInstructorDetailById(int id) {
        //fetch the instructor detail from database
        InstructorDetail instructorDetail = entityManager.find(InstructorDetail.class,id);
        //remove the associated object reference
        //break bidirectional link
        instructorDetail.getInstructor().setInstructorDetail(null);
        //delete the fetched instructor
        entityManager.remove(instructorDetail);
    }

    @Override
    public List<Course> findCoursesByInstructorId(int id) {
        //create query
        TypedQuery<Course> query = entityManager.createQuery("from Course  where instructor.id=:data"
                ,Course.class);
        query.setParameter("data", id);
//        TypedQuery<Course> query = entityManager.createQuery("select u from Course u where u.instructor.id=:data"
//                ,Course.class);
//        query.setParameter("data", id);
        //execute query
        return query.getResultList();

    }

    @Override
    public Instructor findInstructorByIdJoinFetch(int id) {
        //create query
        TypedQuery<Instructor> query = entityManager.
                createQuery("select i from Instructor i " +
                        "join fetch i.courses " +
                        "join fetch i.instructorDetail " +
                        "where i.id =:data", Instructor.class);
        query.setParameter("data",id);
//        String query1 = "select i.id as \"id\", " +
//                        " i.first_name as \"firstName\"," +
//                        " i.last_name as \"lastName\"," +
//                        " i.email as \"email\"" +
//                        " from instructor i" +
//                        " left join course c on i.id=c.instructor_id " +
//                        "left join instructor_detail ind on i.instructor_detail_id=ind.id " +
//                        "where i.id=:data";
//        Query query = entityManager.createNativeQuery(query1);
//        query.setParameter("data",id);


        //execute query
        return query.getSingleResult();

    }
    @Transactional
    @Override
    public void update(Instructor instructor) {
        entityManager.merge(instructor);
    }
    @Transactional
    @Override
    public void update(Course course) {
        entityManager.merge(course);
    }

    @Override
    public Course findCourseById(int id) {
        return entityManager.find(Course.class,id);
    }

    @Transactional
    @Override
    public void deleteCourseById(int id) {
        //fetch the course
        Course course = entityManager.find(Course.class,id);
        //delete the fetched course
        entityManager.remove(course);
    }
    @Transactional
    @Override
    public void saveCourse(Course course) {
        entityManager.persist(course);
    }

    @Override
    public Course findCourseAndReviewsByCourseId(int id) {
        //create query
        TypedQuery<Course> typedQuery = entityManager.createQuery("select c from Course c " +
                                        "join fetch c.reviews " +
                                        " where c.id = :data", Course.class);
        typedQuery.setParameter("data",id);
        //execute query
        return typedQuery.getSingleResult();

    }

    @Override
    public Course findCourseAndStudentsByCourseId(int id) {
        TypedQuery<Course> typedQuery = entityManager.createQuery("select c from Course c " +
                                        "join fetch c.students " +
                                        "where c.id = :data", Course.class);
        typedQuery.setParameter("data",id);
        return typedQuery.getSingleResult();
    }

    @Override
    public Student findStudentAndCoursesByStudentId(int id) {
        TypedQuery<Student> query = entityManager.createQuery("select s from Student s " +
                                    "join fetch s.courses " +
                                    "where s.id = :data", Student.class);
        query.setParameter("data",id);
        return query.getSingleResult();

    }
    @Transactional
    @Override
    public void updateStudent(Student student) {
        entityManager.merge(student);
    }
    @Transactional
    @Override
    public void deleteStudentById(int id) {
        //fetch the student
        Student student = entityManager.find(Student.class,id);
        //delete the student
        entityManager.remove(student);
    }
}
