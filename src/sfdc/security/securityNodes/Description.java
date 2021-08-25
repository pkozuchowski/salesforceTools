package sfdc.security.securityNodes;

import org.w3c.dom.Element;

import java.util.Map;

public class Description extends MetadataNode {
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
        return builder.createElement(getType())
                .setTextContent(value)
                .getElement();
    }

    @Override
    public String getType() {
        return "description";
    }

    @Override
    public String getApiName() {
        return "";
    }
}
