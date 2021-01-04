package org.example.tennis;

import lombok.AllArgsConstructor;
import lombok.Getter;

import static lombok.AccessLevel.PRIVATE;

/**
 * @author <a href="kuldeepyadav7291@gmail.com">Kuldeep</a>
 */
@Getter
@AllArgsConstructor(access = PRIVATE)
public class Player {
    private final String title;
    private Score score;

    public static Player create(String title) {
        return new Player(title, Score.of(0));
    }

    public void winsTheBall() {
        score = score.increment();
    }
}
