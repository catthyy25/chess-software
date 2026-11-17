package tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import software.ulpgc.gameservice.architecture.model.Move;
import software.ulpgc.gameservice.architecture.model.board.Square;
import software.ulpgc.gameservice.infrastructure.adapters.CoordinateMoveDeserializer;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class CoordinateMoveDeserializerTest {
    private CoordinateMoveDeserializer deserializer;

    @BeforeEach
    void setUp() {
        deserializer = new CoordinateMoveDeserializer();
    }

    @Test
    @DisplayName("Debe deserializar un movimiento en notación de coordenadas")
    void shouldDeserializeMove() {
        Move move = deserializer.deserialize("e2e4");
        assertThat(move.from()).isEqualTo(Square.of("e2"));
        assertThat(move.to()).isEqualTo(Square.of("e4"));
    }

    @Test
    @DisplayName("Debe deserializar un movimiento de caballo")
    void shouldDeserializeKnightMove() {
        Move move = deserializer.deserialize("g1f3");
        assertThat(move).isEqualTo(new Move(Square.of("g1"), Square.of("f3")));
    }

    @Test
    @DisplayName("Debe rechazar textos con longitud incorrecta")
    void shouldRejectWrongLength() {
        assertThatThrownBy(() -> deserializer.deserialize("e2"))
                .isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> deserializer.deserialize("e2e4q"))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("Debe rechazar un texto vacío")
    void shouldRejectEmptyText() {
        assertThatThrownBy(() -> deserializer.deserialize(""))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("Debe rechazar casillas que no existen")
    void shouldRejectInvalidSquares() {
        assertThatThrownBy(() -> deserializer.deserialize("z9z9"))
                .isInstanceOf(IllegalArgumentException.class);
    }
}