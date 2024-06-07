import java.util.Scanner;

public class Game {
    // essential var
    public static int[] positions = new int[9];
   
    // user input vars
    private static boolean error = true;
    private static int choice;
    private static String input;

    // game vars
    private static boolean win = false;
    private static boolean tie = false;

    // scanner
    public static Scanner UI = new Scanner(System.in);

    public static void Game(int[] positions, Player A, Player B) {
        introduction(A, B);
        printTable(A, B);

        while (win == false) {
            yourTurn(A);
            printTable(A, B);
            checkWin(positions, A, B);

            yourTurn(B);
            printTable(A, B);
            checkWin(positions, A, B);
        }
    }

    // House Keeping
    public static void input(int setting) {
        switch (setting) {
            case 0: // word input
                System.out.print("Your team name: ");
                input = UI.next();
                break;
            case 1: // num input
                System.out.print("Your choice: ");
                input = UI.nextLine();
                // if the input is a number, convert it to int (so that words don't break the
                // game)
                if (input.equals("1") || input.equals("2") || input.equals("3") || input.equals("4")
                        || input.equals("5") || input.equals("6") || input.equals("7") || input.equals("8")
                        || input.equals("9")) {
                    choice = Integer.parseInt(input);
                } else {
                    choice = 0;
                }
                break;
        }
    }

    /////// RunTime Functions ///////
    // player initiated
    public static void introduction(Player A, Player B) {
        System.out.println("-------------------");
        System.out.println("|  TICK TACK TOE  |");
        System.out.println("-------------------");

        setTeamName(A);
        setTeamName(B);
    }

    public static void setTeamName(Player X) {
        System.out.println("\n\n" + X.getName() + " Selection: ");
        System.out.println("What would you like your team name to be:");
        input(0);
        X.setName(input);
    }

    public static void yourTurn(Player X) {
        System.out.println("\n");
        System.out.println(X.getName() + "'s Turn: ");
        System.out.println("Place your symbol: (select a number)");

        // to print options
        for (int b = 0; b <= 8; b++) {
            if (positions[b] > 0) {
                System.out.println("[-] position #" + (b + 1));
            } else {
                System.out.println("[" + (b + 1) + "] position #" + (b + 1));
            }
        }

        // to choose
        while (error) {
            input(1);
            // to choose
            if (choice >= 1 && choice <= 9) {
                if (positions[choice - 1] > 0) {
                    System.out.println("This position has already been selected.");
                } else {
                    addMark(positions, X, choice);
                    error = false;
                }
            } else {
                System.out.println("[!] Invalid input, please try again");
            }
        }

        error = true;
    }

    public static void addMark(int[] arr, Player X, int choice) {
        if (choice >= 1 && choice <= 9) {
            arr[choice - 1] = X.getNumVal();
        }
    }

    // game initiated
    public static void checkWin(int[] arr, Player A, Player B) {
        // VERTICAL
        for (int a = 0; a <= 2; a++) {
            if (arr[a] == arr[3 + a] && arr[3 + a] == arr[6 + a]) {
                compare(arr, a, A, B);
            }
        }

        // HORIZONTAL
        for (int b = 0; b <= 1; b += 3) {
            if (arr[b] == arr[b + 1] && arr[b + 1] == arr[b + 2]) {
                compare(arr, b, A, B);
            }
        }

        // DIAGONAL
        for (int c = 0; c <= 1; c += 2) {
            if (arr[c] == arr[4] && arr[4] == arr[8 - c]) {
                compare(arr, c, A, B);
            }
        }
    }

    public static String checkSymbol(int[] arr, int position, Player A, Player B) {
        // 0 for empty
        // 1 for team A
        // 2 for team B

        // if empty print out position number to make selection easier, else print out
        // the symbols according to the team's stats

        if (arr[position] == 1) { // team A
            return A.getSymbol();
        } else if (arr[position] == 2) { // team B
            return B.getSymbol();
        } else {
            return Integer.toString(position + 1);
        }
    }

    public static void compare(int[] arr, int position, Player A, Player B) {
        if (arr[position] == A.getNumVal()) {
            A.addWin();
            System.out.println("\n" + A.getName() + " wins this round!");
            newGame(A, B);
        } else if (arr[position] == A.getNumVal()) {
            B.addWin();
            System.out.println("\n" + B.getName() + " wins this round!");
            newGame(A, B);
        }
    }

    public static void printTable(Player A, Player B) {
        System.out.println("\n\n");
        System.out.println(" " + checkSymbol(positions, 0, A, B) + " | " + checkSymbol(positions, 1, A, B) + " | "
                + checkSymbol(positions, 2, A, B) + " ");
        System.out.println("---|---|---");
        System.out.println(" " + checkSymbol(positions, 3, A, B) + " | " + checkSymbol(positions, 4, A, B) + " | "
                + checkSymbol(positions, 5, A, B) + " ");
        System.out.println("---|---|---");
        System.out.println(" " + checkSymbol(positions, 6, A, B) + " | " + checkSymbol(positions, 7, A, B) + " | "
                + checkSymbol(positions, 8, A, B) + " ");
    }

    public static void newGame(Player A, Player B) {
        System.out.println("Would you like to play another round?");
        while (error) {
            System.out.println("[1] yes");
            System.out.println("[2] no");
            input(1);

            switch (choice) {
                case 1: // yes
                    System.out.println("");
                    System.out.println("------------------------");
                    System.out.println(" " + A.getName() + ": " + "\n " + A.getWins() + " wins");
                    System.out.println("");
                    System.out.println(" " + B.getName() + ": " + "\n " + B.getWins() + " wins");
                    System.out.println("------------------------");

                    resetGame(A, B);
                    error = false;
                    break;
                case 2: // no
                    System.out.println("\n" + "Thank you for playing");
                    System.out.println("Now Exiting Program.....");
                    System.exit(0);
                    break;
                default:
                    System.out.println("[!] invalid input");
                    break;
            }
        }

        error = true;
    }

    public static void resetGame(Player A, Player B) {
        for (int a = 0; a <= 8; a++) {
            positions[a] = -1;
        } 
        Game(positions, A, B);
    }
}
