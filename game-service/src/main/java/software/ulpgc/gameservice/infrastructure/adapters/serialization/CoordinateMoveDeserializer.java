package software.ulpgc.gameservice.infrastructure.adapters.serialization;

import software.ulpgc.gameservice.architecture.model.Move;
import software.ulpgc.gameservice.architecture.model.board.Square;
import software.ulpgc.gameservice.architecture.ports.MoveDeserializer;

import java.util.regex.Pattern;

public class CoordinateMoveDeserializer implements MoveDeserializer {
    private static final Pattern FORMAT = Pattern.compile("[a-h][1-8][a-h][1-8]");

    @Override
    public Move deserialize(String value) {
        if (!FORMAT.matcher(value).matches())
            throw new IllegalArgumentException("Invalid move: " + value);
        return new Move(squareIn(value, 0), squareIn(value, 2));
    }

    private Square squareIn(String value, int index) {
        return Square.of(value.substring(index, index + 2));
    }
}