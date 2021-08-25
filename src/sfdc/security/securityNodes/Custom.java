package sfdc.security.securityNodes;

import org.w3c.dom.Element;

import java.util.Map;

public class Custom extends MetadataNode {
    private Boolean value;

    protected Custom() {
    }

    public Custom(Boolean value) {
        this.value = value;
    }

    @Override
    protected void initialize(Map<String, String> nodeValues) {
        this.value = Boolean.valueOf(nodeValues.get("textContent"));
    }

    @Override
    public Element getElement(ElementBuilder builder) {
        return builder.createElement(getType())
                .setTextContent("" + value)
                .getElement();
    }

    @Override
    public String getType() {
        return "custom";
    }

    @Override
    public String getApiName() {
        return "";
    }
}
