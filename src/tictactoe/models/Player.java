package tictactoe.models;

import java.util.Scanner;

public class Player {
    private String name;
    private int id;
    private Symbol symbol;
    private Scanner scanner;

    public Player(String name, int id, Symbol symbol) {
        this.name = name;
        this.id = id;
        this.symbol = symbol;
        this.playerType = PlayerType.Human;
        this.scanner= new Scanner(System.in);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Symbol getSymbol() {
        return symbol;
    }

    public void setSymbol(Symbol symbol) {
        this.symbol = symbol;
    }

    public PlayerType getPlayerType() {
        return playerType;
    }

    public void setPlayerType(PlayerType playerType) {
        this.playerType = playerType;
    }

    private PlayerType playerType;

    public Move makeMove(Board board) {
        System.out.println("Please enter where you want to put your symbol");

        int row=scanner.nextInt();
        int col=scanner.nextInt();
        return new Move(new Cell(row,col),this.symbol,this);

    }
}
