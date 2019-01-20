package sfdc.profiles.profileNodes;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class LayoutAssignment extends ProfileNode {
    private String sObject;
    private String layout;
    private String recordType;

    public LayoutAssignment(String layout) {
        this.layout = layout;
    }

    public LayoutAssignment(Node node) {
        this.sObject = "";
        this.layout = "";
        this.recordType = "";

        NodeList childNodes = node.getChildNodes();

        for (int i = 0; i < childNodes.getLength(); i++) {
            Node childNode = childNodes.item(i);
            String name = childNode.getNodeName(),
                    value = childNode.getTextContent();

            if (name == "layout") {
                this.layout = value;

            } else if (name == "recordType") {
                this.recordType = value;
            }
        }

        this.sObject = this.layout.substring(0, this.layout.indexOf("-"));
    }

    @Override
    public Element getElement(ElementBuilder builder) {
        builder.createElement(getNodeName())
                .addChild("layout", layout);

        if (recordType != "") {
            builder.addChild("recordType", recordType);
        }

        return builder.getElement();
    }

    @Override
    protected String getNodeName() {
        return "layoutAssignments";
    }

    @Override
    protected String getMetadataName() {
        return sObject + recordType;
    }
}
