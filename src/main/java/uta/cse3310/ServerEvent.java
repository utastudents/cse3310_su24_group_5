package uta.cse3310;

public class ServerEvent {
    private PlayerType playerType;
    private int gameId;

    public ServerEvent(PlayerType playerType, int gameId) {
        this.playerType = playerType;
        this.gameId = gameId;
    }

    public PlayerType getPlayerType() {
        return playerType;
    }

    public int getGameId() {
        return gameId;
    }
}

