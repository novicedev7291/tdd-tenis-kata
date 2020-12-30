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
    private int score;

    public static Player create() {
        return new Player(0);
    }

    public void winsTheBall() {
        score++;
    }
}
