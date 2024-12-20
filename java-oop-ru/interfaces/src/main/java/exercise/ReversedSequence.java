package exercise;

// BEGIN
public class  ReversedSequence implements CharSequence {
    private String text;

    public ReversedSequence(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        String result = new StringBuilder(text).reverse().toString();
        return result;
    }

    @Override
    public char charAt(int index) {
        if (index < 0 || index >= text.length()) {
            throw new IndexOutOfBoundsException("Index out of bounds: " + index);
        }
        return text.charAt(index);
    }
    @Override
    public CharSequence subSequence(int start, int end) {
        if (start < 0 || end > text.length() || start > end) {
            throw new IndexOutOfBoundsException("Invalid subsequence range: " + start + " to " + end);
        }
        return text.substring(start, end);
    }
    @Override
    public int length() {
        return text.length();
    }
}
// END
