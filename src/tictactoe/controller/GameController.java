package tictactoe.controller;

import tictactoe.Exceptions.BotCountMoreThanOneException;
import tictactoe.Exceptions.DuplicateSymbolException;
import tictactoe.Exceptions.PlayerCountException;
import tictactoe.models.Game;
import tictactoe.models.Player;
import tictactoe.strategies.WinningStrategy.WinningStrategy;

import java.util.List;

public class GameController {

    public Game startGame(int dimensions, List<Player> players,List<WinningStrategy> winningStrategies) throws BotCountMoreThanOneException, PlayerCountException, DuplicateSymbolException {

        return Game.GetBuilder()
                .setDimensions(dimensions)
                .setPlayers(players)
                .setWinningStrategies(winningStrategies)
                .Build();
    }

    public void makeMove(Game game)
    {
        game.makeMove();

    }

    public void checkGameState(Game game)
    {
        game.getGameState();
    }

    public void printBoard(Game game) {
        game.printBoard();
    }


    public void undo(Game game) {
        game.undo();
    }
}
