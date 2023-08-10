package tictactoe.strategies.BotPlayingStrategy;

import tictactoe.models.*;

import java.util.List;

public class EasyBotPlayingStrategy implements  BotPlayingStrategy{
    @Override
    public Move makeMove(Board board, Player player) {
        for(List<Cell> cells: board.getBoard()) {
            for(Cell cell: cells) {
                if(cell.getCellState().equals(CellState.Empty)) {
                    return new Move(cell,player.getSymbol(),player );
                }
            }
        }

        return null;
    }
}
