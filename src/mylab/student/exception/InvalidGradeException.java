package mylab.student.exception;

// Exception 클래스를 상속받은 사용자 정의 예회 클래스
public class InvalidGradeException extends Exception {
    public InvalidGradeException(String message) {
        super(message); // 부모 클래스의 생성자에 오류 메시지 전달함
    }
}