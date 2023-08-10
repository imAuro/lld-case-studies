package tictactoe.models;


import tictactoe.strategies.BotPlayingStrategy.BotPlayingStrategy;
import tictactoe.strategies.BotPlayingStrategy.BotPlayingStrategyFactory;

public class Bot extends Player{
    private BotDifficulty botDifficulty;
    private BotPlayingStrategy botPlayingStrategy;

    public Bot(String name,int id,Symbol symbol, BotDifficulty botDifficulty) {
        super(name, id, symbol);
        this.botDifficulty = botDifficulty;
        this.botPlayingStrategy= BotPlayingStrategyFactory.getBotPlayingStrategy(botDifficulty);
    }

    @Override
    public Move makeMove(Board board) {
        return botPlayingStrategy.makeMove(board,this);
    }
}
