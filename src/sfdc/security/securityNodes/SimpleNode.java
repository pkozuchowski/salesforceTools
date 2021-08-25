package sfdc.security.securityNodes;

import org.w3c.dom.Element;

import java.util.Map;

public class SimpleNode extends MetadataNode {
    private String name;
    private String value;

    public SimpleNode(String name) {
        this.name = name;
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
        return name;
    }

    @Override
    public String getApiName() {
        return "";
    }
}
