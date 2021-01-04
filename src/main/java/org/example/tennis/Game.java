package org.example.tennis;

import io.vavr.control.Either;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.IntStream;

import static org.example.tennis.Rules.bothPlayerHaveSameScore;
import static org.example.tennis.Rules.defaultScoring;
import static org.example.tennis.Rules.deuceInMatch;
import static org.example.tennis.Rules.onePlayerHasAdvantage;
import static org.example.tennis.Rules.onePlayerWinsTheMatch;

/**
 * @author <a href="kuldeepyadav7291@gmail.com">Kuldeep</a>
 */
@Getter
public class Game {
    private final Player playerA;
    private final Player playerB;
    private final List<Rules> rules = new ArrayList<>(5);

    private Game(Player playerA, Player playerB) {
        this.playerA = playerA;
        this.playerB = playerB;

        rules.add(onePlayerWinsTheMatch);
        rules.add(deuceInMatch);
        rules.add(onePlayerHasAdvantage);
        rules.add(bothPlayerHaveSameScore);
        rules.add(defaultScoring);
    }

    public static Game startWith(Player playerA, Player playerB) {
        Objects.requireNonNull(playerA, "Players cannot be null");
        Objects.requireNonNull(playerB, "Players cannot be null");
        return new Game(playerA, playerB);
    }

    public String result() {
        Score playerAScore = playerA.getScore();
        Score playerBScore = playerB.getScore();

        RuleEngine engine = RuleEngine.applyTo(this)
                .first(onePlayerWinsTheMatch)
                .next(deuceInMatch)
                .next(onePlayerHasAdvantage)
                .next(bothPlayerHaveSameScore)
                .last(defaultScoring);



        return "";
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
