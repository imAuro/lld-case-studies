package tictactoe.strategies.WinningStrategy;

import tictactoe.models.Board;
import tictactoe.models.Move;
import tictactoe.models.Symbol;

import java.util.HashMap;
import java.util.Map;

public class RowWinningStrategy implements WinningStrategy{
    private Map<Integer, Map<Symbol,Integer>>countMap=new HashMap<>();

    @Override
    public void handleUndo(Board board, Move move) {
        int row=move.getCell().getRow();
        var symbol=move.getSymbol();

        var rowMap=countMap.get(row);
        rowMap.put(symbol,rowMap.get(symbol)-1);
    }

    @Override
    public boolean checkWinner(Board board, Move move) {
        int row=move.getCell().getRow();
        Symbol symbol=move.getSymbol();

        if(!countMap.containsKey(row))
        {
            countMap.put(row,new HashMap<>());
        }
        Map<Symbol,Integer> rowMap=countMap.get(row);
        if(!rowMap.containsKey(symbol))
        {
            rowMap.put(symbol,0);
        }
        rowMap.put(symbol,rowMap.get(symbol)+1);

        if(rowMap.get(symbol)==board.getSize())
        {
            return true;
        }




        return false;
    }
}
