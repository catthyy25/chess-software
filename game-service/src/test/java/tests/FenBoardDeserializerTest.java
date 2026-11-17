package tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import software.ulpgc.gameservice.architecture.model.Board;
import software.ulpgc.gameservice.architecture.model.board.Piece;
import software.ulpgc.gameservice.architecture.model.board.Square;
import software.ulpgc.gameservice.infrastructure.adapters.serialization.FenBoardDeserializer;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class FenBoardDeserializerTest {
    private FenBoardDeserializer deserializer;

    @BeforeEach
    void setUp() {
        deserializer = new FenBoardDeserializer();
    }

    @Test
    @DisplayName("Debe deserializar correctamente la posición inicial de ajedrez")
    void shouldDeserializeInitialBoard() {
        String fenInitial = "rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1";
        Board expectedBoard = Board.initial();
        Board actualBoard = deserializer.deserialize(fenInitial);
        assertThat(actualBoard).isEqualTo(expectedBoard);
    }

    @Test
    @DisplayName("Debe deserializar un tablero vacío cuando el FEN indica 8 filas vacías")
    void shouldDeserializeEmptyBoard() {
        Board board = deserializer.deserialize("8/8/8/8/8/8/8/8");
        assertThat(board).isNotNull();
        assertThat(board.pieces()).isEmpty();
    }

    @Test
    @DisplayName("Debe deserializar una posición personalizada con pocas piezas")
    void shouldDeserializeCustomPosition() {
        Board actualBoard = deserializer.deserialize("4k3/8/8/8/8/8/8/4K3 w - - 0 1");
        assertThat(actualBoard.pieces())
                .hasSize(2)
                .containsEntry(Square.of("e1"), Piece.WhiteKing)
                .containsEntry(Square.of("e8"), Piece.BlackKing);
    }

    @Test
    @DisplayName("Debe ignorar los metadatos de FEN (turno, enroques...) y centrarse solo en el tablero")
    void shouldIgnoreFenMetadata() {
        Board actualBoard = deserializer.deserialize("4k3/8/8/8/8/8/8/4K3 b KQkq e3 5 12");
        assertThat(actualBoard.pieces())
                .containsEntry(Square.of("e1"), Piece.WhiteKing)
                .containsEntry(Square.of("e8"), Piece.BlackKing);
    }

    @Test
    @DisplayName("Debe rechazar símbolos inválidos")
    void shouldRejectInvalidSymbols() {
        assertThatThrownBy(() -> deserializer.deserialize("x7/8/8/8/8/8/8/8"))
                .isInstanceOf(IllegalArgumentException.class);
    }
}