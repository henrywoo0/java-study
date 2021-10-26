package loopexample;

public class LoopStar1 {
    public static void main(String[] args) {
        int i, j;

        for(i = 0; i < 4; i++) {
            for(j = i; j < 3; j++) {
                System.out.print(" ");
            }
            for(j = 0; j < i * 2 + 1; j++) {
                System.out.print("*");
            }
            System.out.println();
        }
    }
}
