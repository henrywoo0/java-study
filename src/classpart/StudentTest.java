package classpart;

public class StudentTest {
    public static void main(String[] args) {
        Student student1 = new Student();
        student1.studentName = "이광남";
        System.out.println(student1.getStudentName());

        Student student2 = new Student();
        student2.studentName = "이경태";
        System.out.println(student2.getStudentName());
    }
}
