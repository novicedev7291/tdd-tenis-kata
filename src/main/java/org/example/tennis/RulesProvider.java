package org.example.tennis;

import lombok.NoArgsConstructor;

import static lombok.AccessLevel.PRIVATE;
import static org.example.tennis.Rules.bothPlayerHaveSameScore;
import static org.example.tennis.Rules.defaultScoring;
import static org.example.tennis.Rules.deuceInMatch;
import static org.example.tennis.Rules.onePlayerHasAdvantage;
import static org.example.tennis.Rules.onePlayerWinsTheMatch;

/**
 * @author <a href="kuldeepyadav7291@gmail.com">Kuldeep</a>
 */
@NoArgsConstructor(access = PRIVATE)
class RulesProvider {
    private static final Rules rules = onePlayerWinsTheMatch.appendNext(deuceInMatch)
            .appendNext(onePlayerHasAdvantage)
            .appendNext(bothPlayerHaveSameScore)
            .appendNext(defaultScoring);

    static Rules provide() {
        return rules;
    }
}
