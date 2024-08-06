package uta.cse3310;

public class UserEvent {
    private String action;
    private String value;

    public UserEvent(String action) {
        this.action = action;
    }

    public UserEvent(String action, String value) {
        this.action = action;
        this.value = value;
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
