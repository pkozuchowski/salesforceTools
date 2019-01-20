package sfdc.profiles.profileNodes;

import org.w3c.dom.Element;
import org.w3c.dom.Node;

public class Custom extends ProfileNode {
    private Boolean value;

    public Custom(Boolean value) {
        this.value = value;
    }

    public Custom(Node node) {
        this.value = Boolean.valueOf(node.getTextContent());
    }

    @Override
    public Element getElement(ElementBuilder builder) {
        return builder.createElement(getNodeName())
                .setTextContent(value)
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
