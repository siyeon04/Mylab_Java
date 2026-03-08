package mylab.student.control;

import mylab.student.entity.Student;
import mylab.student.exception.InvalidGradeException;

public class StudentTest {
    public static void main(String[] args) {
        // Student 객체 생성 - 생성자에서 setGrade()가 호출됨
    	Student student = new Student("2024001", "김민수", "컴퓨터공학", 3);
        System.out.println(student.getName() + " / " + student.getMajor() + " / " + student.getGrade() + "학년");

        System.out.println("5학년으로 변경 ");
        try {
            student.setGrade(5); // -> 범위 초과 예외 발생
        } catch (InvalidGradeException e) {
            System.out.println(e.getMessage());
        }
    }
}