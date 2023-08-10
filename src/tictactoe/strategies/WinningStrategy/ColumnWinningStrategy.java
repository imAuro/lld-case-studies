package tictactoe.strategies.WinningStrategy;

import tictactoe.models.Board;
import tictactoe.models.Move;
import tictactoe.models.Symbol;

import java.util.HashMap;

public class ColumnWinningStrategy implements WinningStrategy
{
    HashMap<Integer,HashMap<Symbol,Integer>> countMap=new HashMap<Integer, HashMap<Symbol, Integer>>();
    @Override
    public boolean checkWinner(Board board, Move move) {

        int col=move.getCell().getCol();
        Symbol symbol=move.getSymbol();

        if(!countMap.containsKey(col))
        {
            countMap.put(col,new HashMap<>());
        }
        var colMap=countMap.get(col);
        if(!colMap.containsKey(symbol))
        {
            colMap.put(symbol,0);
        }
        colMap.put(symbol,colMap.get(symbol)+1);
        if(colMap.get(symbol)==board.getSize())
        {
            return true;
        }

        return false;
    }

    @Override
    public void handleUndo(Board board, Move move) {
        int col=move.getCell().getCol();
        var symbol=move.getSymbol();

        var colMap =countMap.get(col);
        colMap.put(symbol, colMap.get(symbol)-1);
    }
}
