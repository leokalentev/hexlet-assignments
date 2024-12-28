package exercise;

import java.util.Map;
import java.util.List;
import java.util.stream.Collectors;

// BEGIN
public class PairedTag extends Tag {
    private String bodyTag;
    private List<Tag> childTag;

    public PairedTag(String nameTag, Map<String, String> attribute, String bodyTag, List<Tag> childTag) {
        super(nameTag, attribute);
        this.bodyTag = bodyTag;
        this.childTag = childTag;
    }

    public String getChildTag() {
        StringBuilder result = new StringBuilder();
        for (int i=0; i < childTag.size(); i++) {
            result.append(childTag.get(i));
        }
        return result.toString();
    }

    public String toString() {
        if (childTag.size() == 0 && getAttribute().isEmpty()) {
            return "<" + getNameTag() + ">" + "</" + getNameTag() + ">";
        } else if (childTag.size() == 0) {
            return "<" + getNameTag() + " " + getAttribute() + ">" + bodyTag + "</" + getNameTag() + ">";
        }
        else {
            return "<" + getNameTag() + " " + getAttribute() + ">" + bodyTag + getChildTag() + "</" + getNameTag() + ">";
        }
    }
}
// END
