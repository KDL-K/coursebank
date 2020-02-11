package by.htp.courselist.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import by.htp.courselist.dao.CourseDAO;
import by.htp.courselist.entity.Course;
import by.htp.courselist.entity.Instructor;
import by.htp.courselist.entity.Student;

@Service
public class CourseServiceImpl implements CourseService{
	
	@Autowired
	private CourseDAO courseDAO;

	@Transactional
	public void addInstructor(Instructor instructor) {
		courseDAO.addInstructor(instructor);
	}

	@Transactional
	public void deleteInstructor(int instructorId) {
		courseDAO.deleteInstructor(instructorId);
	}

	@Transactional
	public void editInstructor(Instructor instructor) {
		courseDAO.editInstructor(instructor);
		
	}
	
	@Transactional
	public Instructor takeInstructor(int instructorId) {
		return courseDAO.takeInstructor(instructorId);
	}

	@Transactional
	public List<Instructor> allInstructors() {
		List<Instructor> instructorList = courseDAO.allInstructors();
		return instructorList;
	}

	@Transactional
	public List<Student> allStudents() {
		List<Student> studentList = courseDAO.allStudents();
		return studentList;
	}

	@Transactional
	public List<Course> allCourses() {
		List<Course> courseList = courseDAO.allCourses();
		return courseList;
	}

	@Transactional
	public List<Student> allStudentsOfCourse(int courseId) {
		List<Student> studentList = courseDAO.allStudentsOfCourse(courseId);
		return studentList;
	}

	@Transactional
	public List<Course> allCoursesOfStudent(int studentId) {
		List<Course> courseList = courseDAO.allCoursesOfStudent(studentId);
		return courseList;
	}
	
	@Transactional
	public List<Course> allCoursesOfInstructor(int instructorId){
		List<Course> courseList = courseDAO.allCoursesOfInstructor(instructorId);
		return courseList;
	}
	
	@Transactional
	public void addCourse(Course course, int instructorId) {
		courseDAO.addCourse(course, instructorId);
	}

	@Transactional
	public void deleteCourse(int courseId) {
		courseDAO.deleteCourse(courseId);
		
	}

}
