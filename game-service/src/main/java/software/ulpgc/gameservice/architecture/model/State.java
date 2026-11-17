package software.ulpgc.gameservice.architecture.model;

import software.ulpgc.gameservice.architecture.model.board.Color;

import java.util.Set;

public class State {
    private final Color side;
    private final Set<CastlingRight> castlingRights;

    public State(Color side, Set<CastlingRight> castlingRights) {
        this.side = side;
        this.castlingRights = castlingRights;
    }

    public Color side() {
        return side;
    }

    public Set<CastlingRight> castlingRights() {
        return castlingRights;
    }
}
