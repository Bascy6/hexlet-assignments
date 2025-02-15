package exercise;

import java.util.Map;
import java.util.List;

// BEGIN
class PairedTag extends Tag {
    String body;
    List<Tag> child;

    public PairedTag(String name, Map<String, String> attributes, String body, List<Tag> child) {
        super(name, attributes);
        this.body = body;
        this.child = child;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        String att = attributesToString();
        result.append(att.isEmpty() ? String.format("<%s>", name) : String.format("<%s %s>", name, att));
        if (body != null && !body.isEmpty()) {
            result.append(body);
        }
        for (Tag child : child) {
            result.append(child.toString());
        }
        result.append(String.format("</%s>", name));
        return result.toString();
    }
}
// END
