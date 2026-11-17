package software.ulpgc.gameservice.architecture.model.board;

import java.util.Arrays;

public record Square(File file, Rank rank) {

    public static Square of(String value) {
        return new Square(fileIn(value), rankIn(value));
    }

    public static Square of(int i, int j) {
        return new Square(File.values()[i], Rank.values()[j]);
    }

    private static File fileIn(String value) {
        return fileIn(value.charAt(0));
    }

    private static File fileIn(char c) {
        return Arrays.stream(File.values()).filter(file -> file.symbol() == c).findFirst().orElseThrow();
    }

    private static Rank rankIn(String value) {
        return rankIn(value.charAt(1));
    }

    private static Rank rankIn(char c) {
        return Arrays.stream(Rank.values()).filter(rank -> rank.symbol() == c).findFirst().orElseThrow();
    }
}
