package sfdc.profiles.profileNodes;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class FieldPermission extends ProfileNode {
    private Boolean editable, readable;
    private String field;

    public FieldPermission(String field) {
        this(field, false, false);
    }

    public FieldPermission(String field, Boolean editable, Boolean readable) {
        this.field = field;
        this.editable = editable;
        this.readable = readable;
    }

    public FieldPermission(Node node) {
        NodeList childNodes = node.getChildNodes();

        for (int i = 0; i < childNodes.getLength(); i++) {
            Node childNode = childNodes.item(i);
            String name = childNode.getNodeName(),
                    value = childNode.getTextContent();

            if (name == "editable") {
                this.editable = Boolean.valueOf(value);

            } else if (name == "readable") {
                this.readable = Boolean.valueOf(value);

            } else if (name == "field") {
                this.field = value;
            }
        }
    }

    @Override
    public Element getElement(ElementBuilder builder) {
        return builder.createElement(getNodeName())
                .addChild("editable", editable)
                .addChild("field", field)
                .addChild("readable", readable)
                .getElement();
    }

    @Override
    protected String getNodeName() {
        return "fieldPermissions";
    }

    @Override
    protected String getMetadataName() {
        return field;
    }
}
