import tictactoe.Exceptions.BotCountMoreThanOneException;
import tictactoe.Exceptions.DuplicateSymbolException;
import tictactoe.Exceptions.PlayerCountException;
import tictactoe.controller.GameController;
import tictactoe.models.*;
import tictactoe.strategies.WinningStrategy.ColumnWinningStrategy;
import tictactoe.strategies.WinningStrategy.DiagonalWinningStrategy;
import tictactoe.strategies.WinningStrategy.RowWinningStrategy;
import tictactoe.strategies.WinningStrategy.WinningStrategy;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args)
    {
        GameController gameController=new GameController();
        Scanner scanner=new Scanner(System.in);

        int dimensionOfGame=3;
        List<Player> players=new ArrayList<Player>();
        players.add(new Player("Auro",1,new Symbol('X')));
        players.add(new Bot("GPT",2,new Symbol('O'),BotDifficulty.Easy));

        List<WinningStrategy> winningStrategies=new ArrayList<WinningStrategy>();
        winningStrategies.add(new RowWinningStrategy());
        winningStrategies.add(new ColumnWinningStrategy());
        winningStrategies.add(new DiagonalWinningStrategy());


        Game game=null;
        try{
            game =gameController.startGame(dimensionOfGame,players,winningStrategies);

            while(game.getGameState().equals(GameState.In_Progress))
            {
                gameController.printBoard(game);
                System.out.println("Would you like to do UNDO");
                String undoAnswer= scanner.next();
                if(undoAnswer.equalsIgnoreCase("YES"))
                {
                    gameController.undo(game);
                    continue;
                }
                gameController.makeMove(game);


            }
        } catch (Exception e) {
            System.out.println("Something went wrong");
        }
        if(game.getGameState().equals(GameState.Success))
        {
            System.out.println("The winner is "+ game.getWinner().getName());
        }
        else if(game.getGameState().equals(GameState.Draw))
        {
            System.out.println("The game is drawn");
        }





    }
}