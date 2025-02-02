package exercise;

// BEGIN
public class ReversedSequence implements CharSequence {
    private String text;
    private String reversedText;

    public ReversedSequence(String text) {
        this.text = text;
        this.reversedText = new StringBuilder(text).reverse().toString();
    }

    @Override
    public int length() {
        return reversedText.length();
    }

    @Override
    public char charAt(int index) {
        return reversedText.charAt(index);
    }

    @Override
    public CharSequence subSequence(int start, int end) {
        return new StringBuilder(reversedText.substring(start, end)).toString();
    }

    @Override
    public String toString() {
        return reversedText;
    }
}
// END
