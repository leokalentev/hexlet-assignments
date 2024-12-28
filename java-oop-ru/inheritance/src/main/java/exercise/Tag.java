package exercise;

import java.util.stream.Collectors;
import java.util.Map;

// BEGIN
public class Tag {
    private String nameTag;
    private Map<String, String> attribute;
    //abstract public String toString(){};
    public Tag(String nameTag, Map<String, String> attribute) {
        this.nameTag = nameTag;
        this.attribute = attribute;
    }

    protected String getNameTag(){
        return nameTag;
    }

    protected String getAttribute(){
        StringBuilder result = new StringBuilder();
        for (Map.Entry<String,String> entry : attribute.entrySet()) {
            result.append(entry.getKey()).append("=\"").append(entry.getValue()).append("\" ");
        }

        if (result.length() > 0) {
            result.setLength(result.length() - 1);
        }
        return result.toString();
    }
}
// END
