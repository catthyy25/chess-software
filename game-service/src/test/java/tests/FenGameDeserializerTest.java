package tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import software.ulpgc.gameservice.architecture.model.Board;
import software.ulpgc.gameservice.architecture.model.Game;
import software.ulpgc.gameservice.architecture.model.board.Color;
import software.ulpgc.gameservice.infrastructure.adapters.serialization.FenBoardDeserializer;
import software.ulpgc.gameservice.infrastructure.adapters.serialization.FenGameDeserializer;
import software.ulpgc.gameservice.infrastructure.adapters.serialization.FenPositionDeserializer;
import software.ulpgc.gameservice.infrastructure.adapters.serialization.FenStateDeserializer;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class FenGameDeserializerTest {
    private static final String INITIAL = "rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1";
    private static final String AFTER_E4 = "rnbqkbnr/pppppppp/8/8/4P3/8/PPPP1PPP/RNBQKBNR b KQkq e3 0 1";

    private FenGameDeserializer deserializer;

    @BeforeEach
    void setUp() {
        deserializer = new FenGameDeserializer(
                new FenPositionDeserializer(new FenBoardDeserializer(), new FenStateDeserializer()));
    }

    @Test
    @DisplayName("Debe deserializar una partida vacía cuando el texto está vacío")
    void shouldDeserializeEmptyGame() {
        Game game = deserializer.deserialize("");
        assertThat(game).isEmpty();
    }

    @Test
    @DisplayName("Debe deserializar una partida con una sola posición")
    void shouldDeserializeSinglePosition() {
        Game game = deserializer.deserialize(INITIAL);
        assertThat(game).hasSize(1);
        assertThat(game.iterator().next().board()).isEqualTo(Board.initial());
    }

    @Test
    @DisplayName("Debe deserializar varias posiciones, una por línea, en orden")
    void shouldDeserializeSeveralPositionsInOrder() {
        Game game = deserializer.deserialize(INITIAL + "\n" + AFTER_E4);
        assertThat(game)
                .hasSize(2)
                .extracting(position -> position.state().side())
                .containsExactly(Color.White, Color.Black);
    }

    @Test
    @DisplayName("Debe ignorar las líneas en blanco")
    void shouldIgnoreBlankLines() {
        Game game = deserializer.deserialize("\n" + INITIAL + "\n\n" + AFTER_E4 + "\n");
        assertThat(game).hasSize(2);
    }

    @Test
    @DisplayName("Debe rechazar posiciones con símbolos inválidos")
    void shouldRejectInvalidPositions() {
        assertThatThrownBy(() -> deserializer.deserialize(INITIAL + "\nx7/8/8/8/8/8/8/8 w - - 0 1"))
                .isInstanceOf(IllegalArgumentException.class);
    }
}