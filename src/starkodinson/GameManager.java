package starkodinson;

import java.util.Scanner;

public class GameManager {
    public static void main(String[] args) throws InterruptedException {
        Scanner scanner = new Scanner(System.in);
        Poker.playPoker(scanner);
    }


}
