package by.htp.courselist.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import by.htp.courselist.entity.Course;
import by.htp.courselist.entity.Instructor;
import by.htp.courselist.entity.Student;

@Repository
public class CourseDAOImpl implements CourseDAO{
	
	@Autowired
	SessionFactory sessionFactory;

	public void addInstructor(Instructor instructor) {
		Session session = sessionFactory.getCurrentSession();
		session.save(instructor);
		
	}

	public void deleteInstructor(int instructorId) {
		Instructor instructor = takeInstructor(instructorId);
		Session session = sessionFactory.getCurrentSession();
		session.delete(instructor);
	}
	
	public void editInstructor(Instructor instructor) {
		Session session = sessionFactory.getCurrentSession();
		session.update(instructor);
	}
	
	public Instructor takeInstructor(int instructorId) {
		Session session = sessionFactory.getCurrentSession();
		Query<Instructor> theQuery = session.createQuery("FROM Instructor WHERE id=:instructorId",Instructor.class);
		theQuery.setParameter("instructorId", instructorId);
		Instructor instructor = theQuery.getSingleResult();
		return instructor;
	}

	public List<Instructor> allInstructors() {
		Session session = sessionFactory.getCurrentSession();
		Query<Instructor> theQuery = session.createQuery("FROM Instructor ORDER BY name", Instructor.class);
		List<Instructor> instructorList = theQuery.getResultList();
		return instructorList;
	}
    
	public List<Student> allStudents() {
		Session session = sessionFactory.getCurrentSession();
		Query<Student> theQuery = session.createQuery("FROM Student ORDER BY name", Student.class);
		List<Student> studentList = theQuery.getResultList();
		return studentList;
	}

	public List<Course> allCourses() {
		Session session = sessionFactory.getCurrentSession();
		Query<Course> theQuery = session.createQuery("FROM Course ORDER BY title", Course.class);
		List<Course> courseList = theQuery.getResultList();
		return courseList;
	}

	public List<Student> allStudentsOfCourse(int courseId) {
		Session session = sessionFactory.getCurrentSession();
		Query<Student> theQuery = session.createQuery("SELECT s FROM Student s JOIN s.courseList c WHERE c.id=:id",Student.class);
		theQuery.setParameter("id", courseId);
		List<Student> studentList = theQuery.getResultList();
		return studentList;
	}

	public List<Course> allCoursesOfStudent(int studentId) {
		Session session = sessionFactory.getCurrentSession();
		Query<Course> theQuery = session.createQuery("SELECT c FROM Course c JOIN c.studentList s WHERE s.id=:id",Course.class);
		theQuery.setParameter("id", studentId);
		List<Course> courseList = theQuery.getResultList();
		return courseList;
	}
	
	public List<Course> allCoursesOfInstructor(int instructorId){
		Session session = sessionFactory.getCurrentSession();
		Query<Course> theQuery = session.createQuery("FROM Course WHERE instructor_id=:id", Course.class);
		theQuery.setParameter("id", instructorId);
		List<Course> courseList = theQuery.getResultList();
		return courseList;
	}
	
	public void addCourse(Course course, int instructorId) {
		Session session = sessionFactory.getCurrentSession();
		Instructor instructor = takeInstructor(instructorId);
		course.setInstructor(instructor);
		session.save(course);
	}
	
	public Course takeCourse(int courseId) {
		Session session = sessionFactory.getCurrentSession();
		Query<Course> theQuery = session.createQuery("FROM Course WHERE id=:courseId",Course.class);
		theQuery.setParameter("courseId", courseId);
		Course course = theQuery.getSingleResult();
		return course;
	}

	public void deleteCourse(int courseId) {
		Course course = takeCourse(courseId);
		Session session = sessionFactory.getCurrentSession();
		session.delete(course);
	}

}
