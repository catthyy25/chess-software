package tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import software.ulpgc.gameservice.architecture.model.Board;
import software.ulpgc.gameservice.architecture.model.CastlingRight;
import software.ulpgc.gameservice.architecture.model.Position;
import software.ulpgc.gameservice.architecture.model.board.Color;
import software.ulpgc.gameservice.architecture.model.board.Piece;
import software.ulpgc.gameservice.architecture.model.board.Square;
import software.ulpgc.gameservice.infrastructure.adapters.serialization.FenBoardDeserializer;
import software.ulpgc.gameservice.infrastructure.adapters.serialization.FenPositionDeserializer;
import software.ulpgc.gameservice.infrastructure.adapters.serialization.FenStateDeserializer;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class FenPositionDeserializerTest {
    private FenPositionDeserializer deserializer;

    @BeforeEach
    void setUp() {
        deserializer = new FenPositionDeserializer(new FenBoardDeserializer(), new FenStateDeserializer());
    }

    @Test
    @DisplayName("Debe deserializar correctamente la posición inicial de ajedrez")
    void shouldDeserializeInitialPosition() {
        String fenInitial = "rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1";
        Position position = deserializer.deserialize(fenInitial);
        assertThat(position.board()).isEqualTo(Board.initial());
        assertThat(position.state().side()).isEqualTo(Color.White);
        assertThat(position.state().castlingRights()).containsExactlyInAnyOrder(
                CastlingRight.WHITE_KING_SIDE, CastlingRight.WHITE_QUEEN_SIDE,
                CastlingRight.BLACK_KING_SIDE, CastlingRight.BLACK_QUEEN_SIDE);
    }

    @Test
    @DisplayName("Debe deserializar el turno de las negras cuando el FEN indica b")
    void shouldDeserializeBlackToMove() {
        Position position = deserializer.deserialize("4k3/8/8/8/8/8/8/4K3 b - - 0 1");
        assertThat(position.state().side()).isEqualTo(Color.Black);
    }

    @Test
    @DisplayName("Debe deserializar un conjunto vacío de enroques cuando el FEN indica -")
    void shouldDeserializeNoCastlingRights() {
        Position position = deserializer.deserialize("4k3/8/8/8/8/8/8/4K3 w - - 0 1");
        assertThat(position.state().castlingRights()).isEmpty();
    }

    @Test
    @DisplayName("Debe deserializar una posición personalizada con enroques parciales")
    void shouldDeserializeCustomPosition() {
        Position position = deserializer.deserialize("4k3/8/8/8/8/8/8/4K3 w Kq - 0 1");
        assertThat(position.board().pieces())
                .hasSize(2)
                .containsEntry(Square.of("e1"), Piece.WhiteKing)
                .containsEntry(Square.of("e8"), Piece.BlackKing);
        assertThat(position.state().castlingRights())
                .containsExactlyInAnyOrder(CastlingRight.WHITE_KING_SIDE, CastlingRight.BLACK_QUEEN_SIDE);
    }

    @Test
    @DisplayName("Debe rechazar símbolos inválidos")
    void shouldRejectInvalidSymbols() {
        assertThatThrownBy(() -> deserializer.deserialize("4k3/8/8/8/8/8/8/4K3 w X - 0 1"))
                .isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> deserializer.deserialize("x7/8/8/8/8/8/8/8 w - - 0 1"))
                .isInstanceOf(IllegalArgumentException.class);
    }
}

