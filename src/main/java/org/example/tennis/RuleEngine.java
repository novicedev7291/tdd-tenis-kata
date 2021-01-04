package org.example.tennis;

import io.vavr.control.Either;

/**
 * @author <a href="kuldeepyadav7291@gmail.com">Kuldeep</a>
 */
interface RuleEngine {
    Either<RuleNotApplicable, RuleApplied> process(Player playerA, Player playerB);

    interface First {
        Either<RuleNotApplicable, RuleApplied> apply(Player playerA, Player playerB);
        default First next(Rules nextRule) {
            return nextRule::apply;
        }

        default RuleEngine last(Rules lastRule) {
            return lastRule::apply;
        }
    }
    interface X {
        void apply(Player playerA, Player playerB);

        default First first(Rules firstRule) {
            return firstRule::apply;
        }
    }

    static X applyTo(Game game) {
        Player playerA = game.getPlayerA();
        Player playerB = game.getPlayerB();
        return (playerA, playerB) -> f;
    }
}
