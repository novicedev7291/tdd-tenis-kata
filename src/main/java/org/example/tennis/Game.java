package org.example.tennis;

import io.vavr.control.Either;
import lombok.Getter;

import java.util.Objects;
import java.util.stream.IntStream;

import static io.vavr.API.$;
import static io.vavr.API.Case;
import static io.vavr.API.Match;
import static io.vavr.Patterns.$Left;
import static io.vavr.Patterns.$Right;

/**
 * @author <a href="kuldeepyadav7291@gmail.com">Kuldeep</a>
 */
@Getter
public class Game {
    private final Player playerA;
    private final Player playerB;

    private Game(Player playerA, Player playerB) {
        this.playerA = playerA;
        this.playerB = playerB;
    }

    public static Game startWith(Player playerA, Player playerB) {
        Objects.requireNonNull(playerA, "Players cannot be null");
        Objects.requireNonNull(playerB, "Players cannot be null");
        return new Game(playerA, playerB);
    }

    public String result() {
        final Rules rules = RulesProvider.provide();

        final Either<RuleNotApplicable, RuleApplied> result = rules.apply(playerA, playerB);

        return Match(result).of(
                Case($Left($()), () -> "Could not determine result"),
                Case($Right($()), RuleApplied::getResult)
        );
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
