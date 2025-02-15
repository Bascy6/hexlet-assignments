package exercise;

import java.util.Map;

// BEGIN
class SingleTag extends Tag {

    public SingleTag(String name, Map<String, String> attributes) {
        super(name, attributes);
    }

    @Override
    public String toString() {
        String att = attributesToString();
        return att.isEmpty() ? String.format("<%s>", name) : String.format("<%s %s>", name, att);
    }
}
// END
