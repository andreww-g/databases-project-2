package bd2.tools;

public class ParseTools {

    public static String extractBetween(String phrase, char firstSymbol, char secondSymbol) {
        return phrase.substring(phrase.indexOf(firstSymbol) + 1, phrase.indexOf(secondSymbol));
    }
}
