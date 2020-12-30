package org.example.tennis;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author <a href="kuldeepyadav7291@gmail.com">Kuldeep</a>
 */
class GameTest {
    private final Player playerA = Player.create();
    private final Player playerB = Player.create();

    private final Game game = Game.startWith(playerA, playerB);

    @Test
    void shouldStartNewGameWithLoveAll() {
        assertThat(game.result()).isEqualTo("love All");
    }

    @Test
    void testPlayerOneScoreFifteenFirst() {
        playerA.winsTheBall();

        assertThat(game.result()).isEqualTo("fifteen:love");
    }

    @Test
    void testWhenBothPlayerScoredShouldBeFifteenAll() {
        playerA.winsTheBall();
        playerB.winsTheBall();

        assertThat(game.result()).isEqualTo("fifteen All");
    }

    @ParameterizedTest
    @CsvSource(value = {
            ",BBBB,Player B wins",
            "AA,BBBB,Player B wins",
            "AAA,BBBBB,Player B wins",
            "AAA,BBB,Deuce",
            "AAA,BB,forty:thirty",
            "AAAA,BBB,Advantage Player A"
    })
    void testExpectedGameResultGivenScoringPatterns(String playerAScores, String playerBScores, String expected) {
        game.apply(playerAScores, playerBScores);

        assertThat(game.result()).isEqualTo(expected);

    }
}
