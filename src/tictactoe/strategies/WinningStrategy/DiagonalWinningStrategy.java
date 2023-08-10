package tictactoe.strategies.WinningStrategy;

import tictactoe.models.Board;
import tictactoe.models.Move;
import tictactoe.models.Symbol;

import java.util.HashMap;

public class DiagonalWinningStrategy implements WinningStrategy{

    HashMap<Symbol,Integer> left=new HashMap<>();
    HashMap<Symbol,Integer> right=new HashMap<>();
    @Override
    public boolean checkWinner(Board board, Move move) {
        int row=move.getCell().getRow();
        int col=move.getCell().getCol();
        Symbol symbol=move.getSymbol();

        if(row==col)
        {
            if(!left.containsKey(symbol))
            {
                left.put(symbol,0);
            }
            left.put(symbol,left.get(symbol)+1);

            if(left.get(symbol)== board.getSize()) {
             return true;
         }
        }
        if(row+ col==board.getSize()-1)
        {
            if(!right.containsKey(symbol))
            {
                right.put(symbol,0);
            }
            right.put(symbol,right.get(symbol)+1);
            if(right.get(symbol)==board.getSize())
            {
                return true;
            }
        }
        return false;
    }

    @Override
    public void handleUndo(Board board, Move move) {
        int row= move.getCell().getRow();
        int col= move.getCell().getCol();
        Symbol symbol=move.getSymbol();
        if(row==col)
        {
            left.put(symbol,left.get(symbol)-1);
        }

        if(row+col==board.getSize()-1)
        {
            right.put(symbol,right.get(symbol)-1);
        }
    }
}
