public class Player {
    private String name;
    private int wins;
    private String symbol;
    private int numVal;

    public Player(String name, int wins, String symbol, int numVal) {
        this.name = name;
        this.wins = wins;
        this.symbol = symbol;
        this.numVal = numVal;
    }

    // setters
    public void setName(String nameIn) {
        this.name = nameIn;
    }

    public void addWin() {
        this.wins = wins++;
    }

    // getters

    public String getName() {
        return name;
    }

    public int getWins() {
        return wins;
    }

    public String getSymbol() {
        return symbol;
    }

    public int getNumVal() {
        return numVal;
    }

}