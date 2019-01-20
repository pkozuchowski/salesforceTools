package sfdc.profiles.profileNodes;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class UserPermission extends ProfileNode {
    private String name;
    private Boolean enabled;

    public UserPermission(String name) {
        this(name, false);
    }

    public UserPermission(String name, Boolean enabled) {
        this.name = name;
        this.enabled = enabled;
    }

    public UserPermission(Node node) {
        NodeList childNodes = node.getChildNodes();

        for (int i = 0; i < childNodes.getLength(); i++) {
            Node childNode = childNodes.item(i);
            String name = childNode.getNodeName(),
                    value = childNode.getTextContent();

            if (name == "name") {
                this.name = value;

            } else if (name == "enabled") {
                this.enabled = Boolean.valueOf(value);
            }
        }
    }

    @Override
    public Element getElement(ElementBuilder builder) {
        return builder.createElement(getNodeName())
                .addChild("enabled", enabled)
                .addChild("name", name)
                .getElement();
    }


    @Override
    protected String getNodeName() {
        return "userPermissions";
    }

    @Override
    protected String getMetadataName() {
        return name;
    }
}
