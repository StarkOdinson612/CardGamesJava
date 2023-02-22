package starkodinson;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class GameManager {
    public static void main(String[] args) throws IOException, InterruptedException {
        Scanner scanner = new Scanner(System.in);
        Poker.playPoker(scanner);
    }


}
