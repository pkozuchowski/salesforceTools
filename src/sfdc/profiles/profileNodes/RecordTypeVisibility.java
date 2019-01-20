package sfdc.profiles.profileNodes;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class RecordTypeVisibility extends ProfileNode {
    private String recordType;
    private Boolean
            isDefault,
            visible;

    public RecordTypeVisibility(String recordType) {
        this(recordType, false, false);
    }

    public RecordTypeVisibility(String recordType, Boolean isDefault, Boolean visible) {
        this.recordType = recordType;
        this.isDefault = isDefault;
        this.visible = visible;
    }

    public RecordTypeVisibility(Node node) {
        NodeList childNodes = node.getChildNodes();

        for (int i = 0; i < childNodes.getLength(); i++) {
            Node childNode = childNodes.item(i);
            String name = childNode.getNodeName(),
                    value = childNode.getTextContent();

            if (name == "recordType") {
                this.recordType = value;

            } else if (name == "default") {
                this.isDefault = Boolean.valueOf(value);

            } else if (name == "visible") {
                this.visible = Boolean.valueOf(value);
            }
        }
    }

    @Override
    public Element getElement(ElementBuilder builder) {
        return builder.createElement(getNodeName())
                .addChild("default", isDefault)
                .addChild("recordType", recordType)
                .addChild("visible", visible)
                .getElement();
    }

    @Override
    protected String getNodeName() {
        return "recordTypeVisibilities";
    }

    @Override
    protected String getMetadataName() {
        return recordType;
    }
}
