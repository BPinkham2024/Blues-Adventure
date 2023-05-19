package gamestates;

import core.UserPanel;

public class State {

    protected UserPanel game;
    
    public State(UserPanel game) {
        this.game = game;
    }

    public UserPanel getGame() {
        return game;
    }
}
