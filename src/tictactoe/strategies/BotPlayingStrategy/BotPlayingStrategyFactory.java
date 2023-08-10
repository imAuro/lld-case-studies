package tictactoe.strategies.BotPlayingStrategy;

import tictactoe.models.BotDifficulty;

public class BotPlayingStrategyFactory {

    public static BotPlayingStrategy getBotPlayingStrategy(BotDifficulty difficulty)
    {
        if(difficulty==BotDifficulty.Hard)
            return new HardBotPlayingStrategy();
        if(difficulty==BotDifficulty.Easy)
            return new EasyBotPlayingStrategy();
        if(difficulty==BotDifficulty.Medium)
            return new MediumBotPlayingStrategy();

        return null;
    }

}
