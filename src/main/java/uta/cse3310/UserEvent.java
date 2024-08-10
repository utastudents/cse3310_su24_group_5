package uta.cse3310;

public class UserEvent {
    private String action;
    private String value;
    private String playerId;  // Add playerId field

    public UserEvent(String action, String value, String playerId) {
        this.action = action;
        this.value = value;
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

    public String getPlayerId() {
        return playerId;
    }

    public void setPlayerId(String playerId) {
        this.playerId = playerId;
    }
}
