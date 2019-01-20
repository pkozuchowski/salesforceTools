package sfdc.profiles.profileNodes;

import org.w3c.dom.Element;
import org.w3c.dom.Node;

public class Description extends ProfileNode {
    private String value;

    public Description(String value) {
        this.value = value;
    }

    public Description(Node node) {
        this.value = node.getTextContent();
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
