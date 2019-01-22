package sfdc.profiles.profileNodes;

import org.w3c.dom.Element;
import org.w3c.dom.Node;

import java.util.Map;

public class Custom extends ProfileNode {
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
        return builder.createElement(getNodeName())
                .setTextContent("" + value)
                .getElement();
    }

    @Override
    protected String getNodeName() {
        return "custom";
    }

    @Override
    protected String getMetadataName() {
        return "";
    }
}
