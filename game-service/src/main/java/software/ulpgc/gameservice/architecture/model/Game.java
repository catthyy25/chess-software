package software.ulpgc.gameservice.architecture.model;

import org.jetbrains.annotations.NotNull;

import java.util.Iterator;
import java.util.List;

public class Game implements Iterable<Position> {
    private List<Move> move;

    @NotNull
    @Override
    public Iterator<Position> iterator() {
        return null;
    }
}
