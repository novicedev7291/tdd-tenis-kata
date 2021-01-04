package org.example.tennis;

import lombok.Value;

/**
 * @author <a href="kuldeepyadav7291@gmail.com">Kuldeep</a>
 */
@Value(staticConstructor = "of")
class Score {
    int value;

    public Score increment() {
        return Score.of(value +1);
    }

    public String translate() {
        switch (value) {
            case 0: return "love";
            case 1: return "fifteen";
            case 2: return "thirty";
            case 3: return "forty";
            default: throw new IllegalArgumentException("Unknown scoring point");
        }
    }
}
