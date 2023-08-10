package tictactoe.strategies.BotPlayingStrategy;

import tictactoe.models.Board;
import tictactoe.models.Move;
import tictactoe.models.Player;

public interface BotPlayingStrategy {
    public Move makeMove(Board board, Player player);


}
