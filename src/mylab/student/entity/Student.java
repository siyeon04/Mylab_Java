package mylab.student.entity;

import mylab.student.exception.InvalidGradeException;

public class Student {
	// 캡슐화 - 외부에서 직접 접근하지 못하도록 private 선언
    private String studentId;
    private String name;
    private String major;
    private int grade;

    public Student(String studentId, String name, String major, int grade) {
        this.studentId = studentId;
        this.name = name;
        this.major = major;
        // setGrade()는 예외를 던질 수 있으므로 생성자 안에 try-catch
        try {
            setGrade(grade);
        } catch (InvalidGradeException e) {
            System.out.println(e.getMessage());
        }
    }
    
    //getter: private 필드를 외부에서 읽을 수 있도록 공개 메서드 제공
    public String getStudentId() { return studentId; }
    //setter: private 필드를 외부에서 변경할 수 있도록 공개 메서드 제공
    public void setStudentId(String studentId) { this.studentId = studentId; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getMajor() { return major; }
    public void setMajor(String major) { this.major = major; }

    public int getGrade() { return grade; }
    // 조건에서 벗어나면 예외 처리
    public void setGrade(int grade) throws InvalidGradeException {
        if (grade < 1 || grade > 4) {
        	// 조건 위반 시 예외 객체를 생성해서 던짐
            throw new InvalidGradeException("학년은 1~4 사이여야 합니다.");
        }
        this.grade = grade;
    }
}