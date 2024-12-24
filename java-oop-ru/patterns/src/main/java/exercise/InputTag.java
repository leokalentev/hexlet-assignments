package exercise;
public class InputTag implements TagInterface{
    private String type;
    private String value;

    public InputTag(String type, String value) {
        this.type = type;
        this.value = value;
    }

    public String getType() {
        return type;
    }

    public String getValue() {
        return value;
    }
    @Override
    public String render() {
        return "<input type=\"" + type + "\" value=\"" + value + "\">";
    }
}
// END
