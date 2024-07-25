package uta.cse3310;

public class ServerEvent {
    public PlayerType YouAre;
    public int GameId;

    public ServerEvent() {
    }

    public ServerEvent(PlayerType player, int gameId) {
        this.YouAre = player;
        this.GameId = gameId;
    }
}
