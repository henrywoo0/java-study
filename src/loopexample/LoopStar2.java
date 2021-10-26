package loopexample;

public class LoopStar2 {
    public static void main(String[] args) {
        int i, j;

        for(i = 0; i < 7; i++) {
            if(i <= 3) {
                for(j = i; j < 3; j++) {
                    System.out.print(" ");
                }
                for(j = 0; j < i * 2 + 1; j++) {
                    System.out.print("*");
                }
            }
            else {
                for(j = 0; j < i - 3; j++) {
                    System.out.print(" ");
                }
                for(j = 0; j < 13 - i * 2; j++)
                    System.out.print("*");
            }
            System.out.println();
        }
    }
}
