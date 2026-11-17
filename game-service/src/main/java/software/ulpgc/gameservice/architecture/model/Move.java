package software.ulpgc.gameservice.architecture.model;

import software.ulpgc.gameservice.architecture.model.board.Square;

public record Move(Square from, Square to) {
}