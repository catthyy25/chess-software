package tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import software.ulpgc.gameservice.architecture.model.CastlingRight;
import software.ulpgc.gameservice.architecture.model.State;
import software.ulpgc.gameservice.architecture.model.board.Color;
import software.ulpgc.gameservice.infrastructure.adapters.FenStateDeserializer;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class FenStateDeserializerTest {
    private FenStateDeserializer deserializer;

    @BeforeEach
    void setUp() {
        deserializer = new FenStateDeserializer();
    }

    @Test
    @DisplayName("Debe deserializar el estado de la posición inicial")
    void shouldDeserializeInitialState() {
        State state = deserializer.deserialize("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1");
        assertThat(state.side()).isEqualTo(Color.White);
        assertThat(state.castlingRights()).containsExactlyInAnyOrder(
                CastlingRight.WHITE_KING_SIDE, CastlingRight.WHITE_QUEEN_SIDE,
                CastlingRight.BLACK_KING_SIDE, CastlingRight.BLACK_QUEEN_SIDE);
    }

    @Test
    @DisplayName("Debe deserializar el turno de las negras cuando el FEN indica b")
    void shouldDeserializeBlackToMove() {
        State state = deserializer.deserialize("4k3/8/8/8/8/8/8/4K3 b - - 0 1");
        assertThat(state.side()).isEqualTo(Color.Black);
    }

    @Test
    @DisplayName("Debe deserializar un conjunto vacío de enroques cuando el FEN indica -")
    void shouldDeserializeNoCastlingRights() {
        State state = deserializer.deserialize("4k3/8/8/8/8/8/8/4K3 w - - 0 1");
        assertThat(state.castlingRights()).isEmpty();
    }

    @Test
    @DisplayName("Debe deserializar enroques parciales")
    void shouldDeserializePartialCastlingRights() {
        State state = deserializer.deserialize("4k3/8/8/8/8/8/8/4K3 w Kq - 0 1");
        assertThat(state.castlingRights())
                .containsExactlyInAnyOrder(CastlingRight.WHITE_KING_SIDE, CastlingRight.BLACK_QUEEN_SIDE);
    }

    @Test
    @DisplayName("Debe rechazar símbolos de enroque inválidos")
    void shouldRejectInvalidCastlingSymbols() {
        assertThatThrownBy(() -> deserializer.deserialize("4k3/8/8/8/8/8/8/4K3 w X - 0 1"))
                .isInstanceOf(IllegalArgumentException.class);
    }
}