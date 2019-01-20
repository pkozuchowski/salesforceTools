package sfdc.profiles.profileNodes;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class ClassAccess extends ProfileNode {
    private String apexClass;
    private Boolean enabled;

    public ClassAccess(String apexClass) {
        this(apexClass, false);
    }

    public ClassAccess(String apexClass, Boolean enabled) {
        this.apexClass = apexClass;
        this.enabled = enabled;
    }

    public ClassAccess(Node node) {
        NodeList childNodes = node.getChildNodes();

        for (int i = 0; i < childNodes.getLength(); i++) {
            Node childNode = childNodes.item(i);
            String name = childNode.getNodeName(),
                    value = childNode.getTextContent();

            if (name == "enabled") {
                this.enabled = Boolean.valueOf(value);

            } else if (name == "apexClass") {
                this.apexClass = value;
            }
        }
    }

    @Override
    public Element getElement(ElementBuilder builder) {
        return builder.createElement(getNodeName())
                .addChild("apexClass", apexClass)
                .addChild("enabled", enabled)
                .getElement();
    }

    @Override
    protected String getNodeName() {
        return "classAccesses";
    }

    @Override
    protected String getMetadataName() {
        return apexClass;
    }
}
