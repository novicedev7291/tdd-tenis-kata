package org.example.tennis;

import lombok.RequiredArgsConstructor;

import java.util.Objects;
import java.util.stream.IntStream;

import static lombok.AccessLevel.PRIVATE;

/**
 * @author <a href="kuldeepyadav7291@gmail.com">Kuldeep</a>
 */
@RequiredArgsConstructor(access = PRIVATE)
public class Game {
    private final Player playerA;
    private final Player playerB;

    public static Game startWith(Player playerA, Player playerB) {
        Objects.requireNonNull(playerA, "Players cannot be null");
        Objects.requireNonNull(playerB, "Players cannot be null");
        return new Game(playerA, playerB);
    }

    public String result() {
        int playerAScore = playerA.getScore();
        int playerBScore = playerB.getScore();

        if(playerAScore >= 4 && playerAScore >= playerBScore + 2) {
            return "Player A wins";
        }else if(playerBScore >= 4 && playerBScore >= playerAScore + 2) {
            return "Player B wins";
        }else if(playerAScore == playerBScore && playerAScore >= 3) {
            return "Deuce";
        }else if(playerAScore >= 4 && playerAScore >= playerBScore + 1) {
            return "Advantage Player A";
        }else if(playerBScore >= 4 && playerBScore >= playerAScore + 1) {
            return "Advantage Player A";
        } else if(playerAScore == playerBScore) {
            return translate(playerAScore) + " All";
        }

        return translate(playerAScore) + ":" + translate(playerBScore);
    }

    private String translate(int score) {
        switch (score) {
            case 0: return "love";
            case 1: return "fifteen";
            case 2: return "thirty";
            case 3: return "forty";
            default: throw new IllegalStateException("Unknown scoring point");
        }
    }

    public void apply(String playerAScores, String playerBScores) {
        int timesPlayerAScored = calculateScore(playerAScores);
        int timesPlayerBScored = calculateScore(playerBScores);

        IntStream.range(0, timesPlayerAScored).forEach(i -> playerA.winsTheBall());
        IntStream.range(0, timesPlayerBScored).forEach(i -> playerB.winsTheBall());
    }

    private int calculateScore(String score) {
        return Objects.isNull(score) ? "".length() : score.length();
    }
}
