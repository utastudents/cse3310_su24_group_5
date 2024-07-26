package uta.cse3310;
//package org.junit.jupiter.api;

public class UserEvent {
    private PlayerType playerType;
    private int id;
    private String playerId;
    private String action;
    private String value;

    public UserEvent(PlayerType playerType, int id) {
        this.playerType = playerType;
        this.id = id;
    }

    public PlayerType getPlayerType() {
        return playerType;
    }

    public int getId() {
        return id;
    }

    public void setPlayerId(String playerId) {
        this.playerId = playerId;
    }

    public String getPlayerId() {
        return playerId;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getAction() {
        return action;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}


