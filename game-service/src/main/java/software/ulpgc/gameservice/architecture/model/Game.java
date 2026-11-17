package software.ulpgc.gameservice.architecture.model;

import org.jetbrains.annotations.NotNull;

import java.util.Iterator;
import java.util.List;

public class Game implements Iterable<Position> {
    private final List<Position> positions;

    public Game(List<Position> positions) {
        this.positions = positions;
    }

    @NotNull
    @Override
    public Iterator<Position> iterator() {
        return positions.iterator();
    }
}