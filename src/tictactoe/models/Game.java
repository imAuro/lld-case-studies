package tictactoe.models;

import tictactoe.Exceptions.BotCountMoreThanOneException;
import tictactoe.Exceptions.DuplicateSymbolException;
import tictactoe.Exceptions.PlayerCountException;
import tictactoe.strategies.WinningStrategy.WinningStrategy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Game {
    private List<Player> players;
    private Board board;
    private GameState gameState;
    private Player winner;
    private int nextPlayerIndex;
    private List<Move> moves;
    private int dimension;
    private List<WinningStrategy> winningStrategies;


    public Game(

                int dimension,
                List<Player> players,
                List<WinningStrategy> winningStrategies) {

        this.board = new Board(dimension);
        this.gameState = GameState.In_Progress;
        this.nextPlayerIndex = 0;
        this.moves = new ArrayList<>();
        this.dimension = dimension;
        this.winningStrategies = winningStrategies;
        this.players=players;

    }

    public static  Builder GetBuilder(){
            return new Builder();
}
    public int getDimension() {
        return dimension;
    }

    public void setDimension(int dimension) {
        this.dimension = dimension;
    }

    public List<WinningStrategy> getWinningStrategies() {
        return winningStrategies;
    }

    public void setWinningStrategies(WinningStrategy winningStrategy) {
        winningStrategies.add(winningStrategy);
    }


    public List<Move> getMoves() {
        return moves;
    }

    public void setMoves(List<Move> moves) {
        this.moves = moves;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public GameState getGameState() {
        return gameState;
    }

    public void setGameState(GameState gameState) {
        this.gameState = gameState;
    }

    public Player getWinner() {
        return winner;
    }

    public void setWinner(Player winner) {
        this.winner = winner;
    }

    public int getNextPlayerIndex() {
        return nextPlayerIndex;
    }

    public void setNextPlayerIndex(int nextPlayerIndex) {
        this.nextPlayerIndex = nextPlayerIndex;
    }

    public Boolean validateMove(Move move)
    {
        if(move.getCell().getCol()>dimension-1 || move.getCell().getRow()>dimension-1)
        {
            return false;
        }
        if(!move.getCell().getCellState().equals(CellState.Empty))
        {
            return false;
        }
        return true;
    }


    public void makeMove()
    {
        Player currentPlayer=this.players.get(nextPlayerIndex);

        System.out.println("Please make your move "+currentPlayer.getName());

        Move currentPlayerMove =currentPlayer.makeMove(board);
        if(!validateMove(currentPlayerMove))
        {
            System.out.println("Try again");
            return;
        }

        int row=currentPlayerMove.getCell().getRow();
        int col=currentPlayerMove.getCell().getCol();
        Cell currentCellUpdate=board.getBoard().get(row).get(col);

        currentCellUpdate.setCellState(CellState.Filled);
        currentCellUpdate.setPlayer(currentPlayer);

        Move finalMoveObject=new Move(currentCellUpdate, currentPlayer.getSymbol(),currentPlayer);
        moves.add(finalMoveObject);

        nextPlayerIndex+=1;
        nextPlayerIndex%=players.size();
        if(checkWinner(board,finalMoveObject))
        {
            gameState=GameState.Success;
            winner=currentPlayer;
            System.out.println("The winner is "+ currentPlayer);
        }
        else if(moves.size()== board.getSize()+ board.getSize())
        {
            gameState=GameState.Draw;
            System.out.println("The game is drawn");
        }
    }

    public void printBoard()
    {
        board.printBoard();
    }

    private boolean checkWinner(Board board,Move move)
    {
        for(WinningStrategy winningStrategy: winningStrategies)
        {
            if(winningStrategy.checkWinner(board,move))
            {
                return true;
            }
        }
        return false;
    }

    public void undo(){
        if(moves.size()==0)
        {
            System.out.println("No moves present to do Undo");
            return;
        }
        Move lastMove=moves.get(moves.size()-1);

        moves.remove(lastMove);
        Cell cell=lastMove.getCell();
        cell.setCellState(CellState.Empty);
        cell.setPlayer(null);

        for(WinningStrategy winningStrategy: winningStrategies)
        {
            winningStrategy.handleUndo(board,lastMove);

        }
        nextPlayerIndex-=1;
        nextPlayerIndex=(nextPlayerIndex+players.size())%players.size();



    }



    public static  class Builder {
        private List<Player> players;
        private List<WinningStrategy> winningStrategies;
        private int dimensions;
        public List<Player> getPlayers() {
            return players;
        }

        public Builder setPlayers(List<Player> players) {
            this.players = players;
            return this;
        }

        public List<WinningStrategy> getWinningStrategies() {
            return winningStrategies;
        }

        public Builder setWinningStrategies(List<WinningStrategy> winningStrategies) {
            this.winningStrategies=winningStrategies;
            return this;
        }

        public int getDimensions() {
            return dimensions;
        }

        public Builder setDimensions(int dimensions) {
            this.dimensions = dimensions;
            return this;
        }


        private void validateBotCount() throws BotCountMoreThanOneException {
            int count=0;
            for(Player p: players)
            {
                if(p.getPlayerType().equals(PlayerType.Bot))
                {
                    count++;
                }
            }

            if(count>1){
                throw new BotCountMoreThanOneException();
            }
        }

        private void validateDimensionAndPlayerCount() throws PlayerCountException {
            if(players.size()!=this.dimensions-1)
            {
                throw new PlayerCountException();
            }
        }

        private void validateSymbolUniqueness() throws DuplicateSymbolException {
            HashMap<Character,Integer> symbolCount=new HashMap<>();
            for(Player p: players)
            {
               if(!symbolCount.containsKey(p.getSymbol()))
                {
                   symbolCount.put(p.getSymbol().getSymbol(),0);
                }
               symbolCount.put(
                       p.getSymbol().getSymbol(), symbolCount.get(p.getSymbol().getSymbol())+1
               );
                if(symbolCount.get(p.getSymbol().getSymbol())>1)
                {
                    throw new DuplicateSymbolException();
                }
            }
        }
        private void validate() throws BotCountMoreThanOneException, PlayerCountException, DuplicateSymbolException {
            validateSymbolUniqueness();
            validateDimensionAndPlayerCount();
            validateBotCount();
        }
        public Game Build() throws BotCountMoreThanOneException, PlayerCountException, DuplicateSymbolException {
           validate();

           return new Game(dimensions,players,winningStrategies);
        }



    }
}
