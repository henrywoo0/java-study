package basic.abstractex;

public class ComputerTest {
    public static void main(String[] args) {
        // Computer c1 = new Computer(); // 오류 발생
        Computer c2 = new DeskTop();
        // Computer c3 = new NoteBook(); // 오류 발생
        Computer c4 = new MyNoteBook();

        c2.turnOn();
        c4.turnOn();

        c2.display();
        c4.display();

        c2.typing();
        c4.typing();
    }
}
