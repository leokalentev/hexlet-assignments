package exercise;

import java.util.Map;

// BEGIN
public class SingleTag extends Tag {
    public SingleTag(String nameTag, Map<String, String> attribute) {
        super(nameTag, attribute);
    }
    public String toString() {
        if (getAttribute().isEmpty()) {
            return "<" + getNameTag() + ">";
        } else {
            return "<" + getNameTag() + " " + getAttribute() + ">";
        }
    }
}
// END
