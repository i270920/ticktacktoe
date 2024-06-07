// Tick Tack Toe Program
// May 31th. 2024
// Ramba Kubari
// The purpose of this program is to display a interactive tick tack toe board.

// TO DO LIST:
/*  1. Check win for tie
    2. Fix win counter
    3. Fix new game to actually complete
 */

public class Main {
    public static void main(String[] args) {
        Player TeamA = new Player("Player 1", 0, "X", 1);
        Player TeamB = new Player("Player 2", 0, "O", 2);

        Game.Game(Game.positions, TeamA, TeamB);
    }
}
