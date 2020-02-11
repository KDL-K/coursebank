package by.htp.courselist.controller;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import by.htp.courselist.entity.Course;
import by.htp.courselist.entity.Instructor;
import by.htp.courselist.entity.Student;
import by.htp.courselist.service.CourseService;

@Controller
public class CourseController {
	
	@Autowired
	private CourseService courseService;
	
	@InitBinder
	public void initBinder(WebDataBinder dataBinder) {
		StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
		dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
	}
	
	@RequestMapping("/show_instructors")
	public ModelAndView showInstructors() {
		ModelAndView modelAndView = new ModelAndView();
		List<Instructor> instructorList = courseService.allInstructors();
		modelAndView.setViewName("instructor");
		modelAndView.addObject("instructors", instructorList);
		modelAndView.addObject("side", "side_instructor");
		return modelAndView;
	}
	
	@RequestMapping("/show_courses")
	public ModelAndView showCourses() {
		ModelAndView modelAndView = new ModelAndView();
		List<Course> courseList = courseService.allCourses();
		modelAndView.setViewName("course");
		modelAndView.addObject("courses", courseList);
		return modelAndView;
	}
	@RequestMapping("/show_students")
	public ModelAndView showStudents() {
		ModelAndView modelAndView = new ModelAndView();
		List<Student> studentList = courseService.allStudents();
		modelAndView.setViewName("student");
		modelAndView.addObject("students", studentList);
		return modelAndView;
	}
	
	@RequestMapping("/add_instructor_page")
	public ModelAndView showAddInstructorPage() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("addinstructor");
		modelAndView.addObject("newInstructor", new Instructor());
		return modelAndView;
	}
	@RequestMapping(value="/add_instructor", method=RequestMethod.POST)
	public String addInstructor(@Valid @ModelAttribute("newInstructor") Instructor instructor, BindingResult bindingResult) {
		
		if(bindingResult.hasErrors()) {
			return "addinstructor";
		}else {
			courseService.addInstructor(instructor);
			return "redirect:/show_instructors";
		}
	}
	@RequestMapping("/instructor_course/{side}/{id}")
	public ModelAndView showCoursesOfInstructor(@PathVariable("id") int instructorId,@PathVariable("side") String side, HttpSession session) {
		List<Course> courseList = courseService.allCoursesOfInstructor(instructorId);
		session.setAttribute("instructorId", instructorId);
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("course");
		modelAndView.addObject("courses", courseList);
		modelAndView.addObject("side", side);
		return modelAndView;
	}
	
	@RequestMapping("/instructor_delete/{id}")
	public ModelAndView deleteInstructor(@PathVariable("id") int instructorId) {
		ModelAndView modelAndView = new ModelAndView();
	    courseService.deleteInstructor(instructorId);
		modelAndView.setViewName("redirect:/show_instructors");
		return modelAndView;
	}
	
	@RequestMapping(value="/instructor_edit", method=RequestMethod.POST)
	public ModelAndView editInstructor(@ModelAttribute("instructor") Instructor instructor) {
		ModelAndView modelAndView = new ModelAndView();
		courseService.editInstructor(instructor);
		modelAndView.setViewName("redirect:/show_instructors");
		return modelAndView;
	}
	
	@RequestMapping(value="/instructor_edit_page/{id}", method=RequestMethod.GET)
	public String editInstructorPage(@PathVariable("id") int instructorId, Model model) {
		Instructor instructor = courseService.takeInstructor(instructorId);
		model.addAttribute("instructor", instructor);
		return "editinstructor";
		
		/*ModelAndView modelAndView = new ModelAndView();
		Instructor instructor = courseService.takeInstructor(instructorId);
		modelAndView.setViewName("editinstructor");
		modelAndView.addObject("instructor", instructor);
		return modelAndView;*/
	}
	
	@RequestMapping("/student_courses/{id}")
	public ModelAndView showCoursesOfStudent(@PathVariable("id") int studentId) {
		List<Course> courseList = courseService.allCoursesOfStudent(studentId);
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("course");
		modelAndView.addObject("courses", courseList);
		modelAndView.addObject("side", "side_student");
		return modelAndView;
	}
	
	@RequestMapping("/course_students/{id}")
	public ModelAndView showStudentsOfCourse(@PathVariable("id") int courseId) {
		List<Student> studentList = courseService.allStudentsOfCourse(courseId);
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("student");
		modelAndView.addObject("students", studentList);
		return modelAndView;
	}

	
	@RequestMapping("/add_course_page")
	public ModelAndView showAddCoursePage() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("addcourse");
		Course course = new Course();
		modelAndView.addObject("newCourse", course);
		return modelAndView;
	}
	
	@RequestMapping(value="/add_course", method=RequestMethod.POST)
	public ModelAndView addCourse(@ModelAttribute("newCourse") Course course, HttpSession session) {
		int instructorId = (Integer)session.getAttribute("instructorId");
		courseService.addCourse(course, instructorId);
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("redirect:/instructor_course/side_instructor/"+course.getInstructor().getId());
		
		return modelAndView;
	}
	
	@RequestMapping("/course_delete/{id}")
	public ModelAndView deleteCourse(@PathVariable("id") int courseId, HttpSession session) {
		int instructorId = (Integer)session.getAttribute("instructorId");
		ModelAndView modelAndView = new ModelAndView();
	    courseService.deleteCourse(courseId);
		modelAndView.setViewName("redirect:/instructor_course/side_instructor/"+instructorId);
		return modelAndView;
	}

}
