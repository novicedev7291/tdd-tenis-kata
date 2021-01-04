package org.example.tennis;

import io.vavr.control.Either;

import java.util.function.BiFunction;

import static org.example.tennis.EitherResult.announceFailure;
import static org.example.tennis.EitherResult.announceSuccess;

/**
 * @author <a href="kuldeepyadav7291@gmail.com">Kuldeep</a>
 */
interface Rules extends BiFunction<Player, Player, Either<RuleNotApplicable, RuleApplied>> {
    Rules onePlayerWinsTheMatch = (playerA, playerB) -> {
        int playerAScore = playerA.getScore().getValue();
        int playerBScore = playerB.getScore().getValue();

        final String pattern = "Player %s wins";

        if (playerAScore >= 4 && playerAScore >= playerBScore + 2) {
            return announceSuccess(new RuleApplied(String.format(pattern, playerA.getTitle())));
        } else if (playerBScore >= 4 && playerBScore >= playerAScore + 2) {
            return announceSuccess(new RuleApplied(String.format(pattern, playerB.getTitle())));
        }
        return announceFailure(new RuleNotApplicable());
    };

    Rules deuceInMatch = (playerA, playerB) -> {
        int playerAScore = playerA.getScore().getValue();
        int playerBScore = playerB.getScore().getValue();

        if (playerAScore == playerBScore && playerAScore >= 3) {
            return announceSuccess(new RuleApplied("Deuce"));
        }
        return announceFailure(new RuleNotApplicable());
    };

    Rules onePlayerHasAdvantage = (playerA, playerB) -> {
        int playerAScore = playerA.getScore().getValue();
        int playerBScore = playerB.getScore().getValue();

        final String pattern = "Advantage Player %s";

        if (playerAScore >= 4 && playerAScore >= playerBScore + 1) {
            return announceSuccess(new RuleApplied(String.format(pattern, playerA.getTitle())));
        } else if (playerBScore >= 4 && playerBScore >= playerAScore + 1) {
            return announceSuccess(new RuleApplied(String.format(pattern, playerB.getTitle())));
        }
        return announceFailure(new RuleNotApplicable());
    };

    Rules bothPlayerHaveSameScore = (playerA, playerB) -> {
        int playerAScore = playerA.getScore().getValue();
        int playerBScore = playerB.getScore().getValue();

        if(playerAScore == playerBScore) return announceSuccess(new RuleApplied(playerA.getScore().translate() + " All"));
        return announceFailure(new RuleNotApplicable());
    };

    Rules defaultScoring = (playerA, playerB) ->
            announceSuccess(new RuleApplied(playerA.getScore().translate() + ":" + playerB.getScore().translate()));
}
