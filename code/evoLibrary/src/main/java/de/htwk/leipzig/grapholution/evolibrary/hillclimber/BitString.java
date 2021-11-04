package de.htwk.leipzig.grapholution.evolibrary.hillclimber;

import java.util.Random;
import java.util.regex.Pattern;

public class BitString {
    private Boolean[] bitArray;

    public BitString() {}

    public void generate(int length) {
        Random rand = new Random();
        bitArray = new Boolean[length];
        for (int i = 0; i < bitArray.length; i++) {
            bitArray[i] = rand.nextBoolean();
        }
    }

    public void fromString(String bitString) {
        if (Pattern.matches("[01]+", bitString)) {
            bitArray = new Boolean[bitString.length()];
            for (int i = 0; i < bitArray.length; i++) {
                bitArray[i] = bitString.charAt(i) == '1';
            }
        } else {
            // TODO
        }
    }

    public String toString() {
        char[] charBits = new char[bitArray.length];

        for (int i = 0; i < charBits.length; i++) {
            if (bitArray[i]) {
                charBits[i] = '1';
            } else {
                charBits[i] = '0';
            }
        }
        return charBits.toString();
    }

    public void flip(int i) {
        bitArray[i] = !bitArray[i];
    }
}
