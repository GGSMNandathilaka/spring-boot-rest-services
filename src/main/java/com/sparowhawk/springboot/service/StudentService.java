package com.sparowhawk.springboot.service;

import com.sparowhawk.springboot.model.Course;
import com.sparowhawk.springboot.model.Student;
import org.springframework.stereotype.Component;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class StudentService {

    private static List<Student> students = new ArrayList<>();

    static {
        // init data

        Course course1 = new Course("Course1", "Spring", "10 Steps",
                Arrays.asList("Learn Maven", "Import projects", "Second Example"));
        Course course2 = new Course("Course2", "Spring MVC", "10 Examples",
                Arrays.asList("Learn Maven", "Import Project", "First Example",
                        "Second Example"));
        Course course3 = new Course("Course3", "Spring Boot", "6K Students",
                Arrays.asList("Learn Maven", "Learn Spring",
                        "Learn Spring MVC", "First Example", "Second Example"));
        Course course4 = new Course("Course4", "Maven",
                "Most popular maven course on internet!", Arrays.asList(
                "Pom.xml", "Build Life Cycle", "Parent POM",
                "Importing into Eclipse"));

        Student student1 = new Student("Student1", "John Doe", "traveler, programmer & architect",
                new ArrayList<>(Arrays.asList(course1, course2, course3, course4)));

        Student student2 = new Student("Student2", "Mark Doe", "traveler, programmer & architect",
                new ArrayList<>(Arrays.asList(course1, course2, course3, course4)));

        students.add(student1);
        students.add(student2);
    }

    /***
     * get whole list of students
     * @return
     */
    public List<Student> retrieveAllStudents() {
        return students;
    }

    /***
     * get specific student
     * @param studentId
     * @return
     */
    public Student retrieveStudent(String studentId) {
        for (Student student : students) {
            if (student.getId().equals(studentId)) {
                return student;
            }
        }

        return null;
    }

    /***
     * get all the courses of a student
     * @param studentId
     * @return
     */
    public List<Course> retrieveCourses(String studentId) {
        Student student = retrieveStudent(studentId);

//        if (studentId.equalsIgnoreCase("Student1")) {
//            throw new RuntimeException("Something went wrong");
//        }

        if (student == null) {
            return null;
        }

        return student.getCourses();
    }

    /***
     * retrieve specific course of a student
     * @param studentId
     * @param courseId
     * @return
     */
    public Course retrieveCourse(String studentId, String courseId) {
        Student student = retrieveStudent(studentId);

        if (student == null) {
            return null;
        }

        for (Course course : student.getCourses()) {
            if (course.getId().equals(courseId)) {
                return course;
            }
        }

        return null;
    }

    private SecureRandom random = new SecureRandom();

    public Course addCourse(String studentId, Course course) {
        Student student = retrieveStudent(studentId);

        if (student == null) {
            return null;
        }

        String randomId = new BigInteger(130, random).toString(32);
        course.setId(randomId);

        student.getCourses().add(course);
        return course;
    }


}
