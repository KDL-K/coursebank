package by.htp.courselist.dao;

import java.util.List;

import by.htp.courselist.entity.Course;
import by.htp.courselist.entity.Instructor;
import by.htp.courselist.entity.Student;

public interface CourseDAO {
	void addInstructor(Instructor instructor);
	void deleteInstructor(int instructorId);
	void editInstructor(Instructor instructor);
	Instructor takeInstructor(int instructorId);
	
	public void addCourse(Course course, int instructorId);
	void deleteCourse(int courseId);
	
	List<Instructor> allInstructors();
	List<Student> allStudents();
	List<Course> allCourses();
	
	List<Student> allStudentsOfCourse(int courseId);
	List<Course> allCoursesOfStudent(int studentId);
	List<Course> allCoursesOfInstructor(int instructorId);
}
