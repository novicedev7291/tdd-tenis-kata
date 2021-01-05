package org.example.tennis;

import io.vavr.control.Either;
import lombok.NoArgsConstructor;

import static io.vavr.control.Either.left;
import static io.vavr.control.Either.right;
import static lombok.AccessLevel.PRIVATE;

/**
 * @author <a href="kuldeepyadav7291@gmail.com">Kuldeep</a>
 */
@NoArgsConstructor(access = PRIVATE)
class EitherResult {
    static <L,R> Either<L,R> announceSuccess(R o) {
        return right(o);
    }

    static <L,R> Either<L,R> announceFailure(L o) {
        return left(o);
    }
}
