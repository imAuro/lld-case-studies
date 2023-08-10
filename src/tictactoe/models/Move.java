package tictactoe.models;

public class Move {
    private Cell cell;
    private Symbol symbol;
    private Player player;

    public Move(Cell cell, Symbol symbol, Player player) {
        this.cell = cell;
        this.symbol = symbol;
        this.player = player;

    }

    public Cell getCell() {
        return cell;
    }

    public void setCell(Cell cell) {
        this.cell = cell;
    }

    public Symbol getSymbol() {
        return symbol;
    }

    public void setSymbol(Symbol symbol) {
        this.symbol = symbol;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }
}
