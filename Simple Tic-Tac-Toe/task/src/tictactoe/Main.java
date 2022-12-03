package tictactoe;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    static Scanner in = new Scanner(System.in);
    public static void main(String[] args) {
        // write your code here
        String game_state = "_________";
        char c = 'X';
        boolean gameFinished = false;

        printGameGrid(game_state);

//        game_status(game_state);
    while (true) {

        int row = 0;
        int col = 0;

        while (true) {

            try {
                row = in.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("You should enter numbers!");
                in.nextLine();
                continue;
            }

            try {
                col = in.nextInt();
            } catch (InputMismatchException e){
                System.out.println("You should enter numbers!");
                in.nextLine();
                continue;
            }

            if (row < 1 || row > 3 || col < 1 || col > 3) {
                System.out.println("Coordinates should be from 1 to 3!");
                continue;
            }

            if (game_state.charAt(RCindex(row, col)) == ' ' || game_state.charAt(RCindex(row, col)) == '_') {
                game_state = update_grid(game_state, row, col, c);
                if (c == 'X') {
                    c = 'O';
                } else c = 'X';

                printGameGrid(game_state);
                break;
            } else {
                System.out.println("This cell is occupied! Choose another one!");
                continue;
            }

        }

        gameFinished = game_status(game_state);

        if (gameFinished) {
            break;
        }
    }

    }

    static void printGameGrid(String game_state) {

        System.out.println("---------");
        int j = 0;

        for (int i = 0; i < game_state.length(); i++) {
            if (j == 0) {
                System.out.print("| " + game_state.charAt(i) + " ");
                j++;
            } else if (j == 1) {
                System.out.print(game_state.charAt(i) + " ");
                j++;
            } else if (j == 2) {
                System.out.println(game_state.charAt(i) + " " + "|");
                j = 0;
            }
        }

        System.out.println("---------");
    }

    static boolean in_row(String s, char c) {
        for (int i = 0; i < 9; i = i + 3) {
            if (s.charAt(i) == c && s.charAt(i + 1) == c && s.charAt(i + 2) == c) {
//                System.out.println(c + " wins");
                return true;
            }
        }
        return false;
    }

    static boolean in_diagonal(String s, char c) {
        if (s.charAt(0) == c && s.charAt(4) == c && s.charAt(8) == c) {
            System.out.println(c + " wins");
            return true;
        } else return s.charAt(2) == c && s.charAt(4) == c && s.charAt(6) == c;
    }

    static boolean in_column(String s, char c) {
        for (int i = 0; i < 3; i++) {
            if (s.charAt(i) == c && s.charAt(i + 3) == c && s.charAt(i + 6) == c) {
//                System.out.println(c + " wins");
                return true;
            }
        }
        return false;
    }
    
    static int nOfXO(String s, char c) {
        int counter = 0;
        for (char t: s.toCharArray()) {
            if (t == c) {
                counter++;
            }
        }
        return counter;
    }

    static boolean impossible_state(String s) {

        if (in_row(s, 'X') && in_row(s, 'O')) {
//          System.out.println("Impossible");
            return true;
        }

        if (in_column(s, 'X') && in_column(s, 'O')) {
//            System.out.println("Impossible");
            return true;
        }

        //            System.out.println("Impossible");
        return (nOfXO(s, 'X') - nOfXO(s, 'O') >= 2) || (nOfXO(s, 'O') - nOfXO(s, 'X') >= 2);
    }

    static boolean xWin(String s) {
        return (in_row(s, 'X') || in_column(s, 'X') || in_diagonal(s, 'X'));
    }

    static boolean oWin(String s) {
        return (in_row(s, 'O') || in_column(s, 'O') || in_diagonal(s, 'O'));
    }

    static boolean isDraw(String s) {
        return (nOfXO(s, 'X') + nOfXO(s, 'O') == 9);
    }

    static boolean game_status(String s) {

        boolean finished = false;

        if (impossible_state(s)) {
            //System.out.println("Impossible");
        } else if (xWin(s)) {
            System.out.println("X wins");
            finished = true;
        } else if (oWin(s)) {
            System.out.println("O wins");
            finished = true;
        } else if (isDraw(s)) {
            System.out.println("Draw");
            finished = true;
        }

        return finished;
    }

    static int RCindex(int row, int col) {

        int index = 0;

        switch (row) {
            case 1:
                index = 0 + col - 1;
                break;

            case 2:
                index = 3 + col - 1;
                break;

            case 3:
                index = 6 + col - 1;
                break;
        }

        return index;
    }

    static String update_grid(String s, int row, int col, char c) {

        int index_to_update = RCindex(row, col);

        char[] s_char_array = s.toCharArray();
        s_char_array[index_to_update] = c;

        String updated_string = new String(s_char_array);

        return updated_string;
    }
}