package sfdc.profiles.profileNodes;

import org.w3c.dom.Element;

import java.util.Map;

public class Description extends ProfileNode {
    private String value;

    protected Description() {
    }

    public Description(String value) {
        this.value = value;
    }

    @Override
    protected void initialize(Map<String, String> nodeValues) {
        this.value = nodeValues.get("textContent");
    }

    @Override
    public Element getElement(ElementBuilder builder) {
        return builder.createElement(getNodeName())
                .setTextContent(value)
                .getElement();
    }

    @Override
    protected String getNodeName() {
        return "description";
    }

    @Override
    protected String getMetadataName() {
        return "";
    }
}
