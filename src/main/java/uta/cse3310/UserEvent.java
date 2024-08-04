package uta.cse3310;

public class UserEvent {
    private PlayerType playerType;
    private int id;
    private String playerId;
    private String action;
    private String value;

    public UserEvent(PlayerType playerType, int id, String action, String value) {
        this.playerType = playerType;
        this.id = id;
        this.action = action;
        this.value = value;
    }

    public PlayerType getPlayerType() {
        return playerType;
    }

    public int getId() {
        return id;
    }

    public String getPlayerId() {
        return playerId;
    }

    public void setPlayerId(String playerId) {
        this.playerId = playerId;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}



