package org.example.tennis;

import io.vavr.control.Either;

import static io.vavr.control.Either.left;
import static io.vavr.control.Either.right;

/**
 * @author <a href="kuldeepyadav7291@gmail.com">Kuldeep</a>
 */
class EitherResult {
    static <L,R> Either<L,R> announceSuccess(R o) {
        return right(o);
    }

    static <L,R> Either<L,R> announceFailure(L o) {
        return left(o);
    }
}
