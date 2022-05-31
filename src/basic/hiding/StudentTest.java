package basic.hiding;

public class StudentTest {
    public static void main(String[] args) {
        Student studentLee = new Student();
        studentLee.setStudentName("우준성");

        System.out.println(studentLee.getStudentName());
    }
}
