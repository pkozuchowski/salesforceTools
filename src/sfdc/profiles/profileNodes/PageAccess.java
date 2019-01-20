package sfdc.profiles.profileNodes;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class PageAccess extends ProfileNode {
    private String apexPage;
    private Boolean enabled;

    public PageAccess(String apexPage) {
        this(apexPage, false);
    }

    public PageAccess(String apexPage, Boolean enabled) {
        this.apexPage = apexPage;
        this.enabled = enabled;
    }

    public PageAccess(Node node) {
        NodeList childNodes = node.getChildNodes();

        for (int i = 0; i < childNodes.getLength(); i++) {
            Node childNode = childNodes.item(i);
            String name = childNode.getNodeName(),
                    value = childNode.getTextContent();

            if (name == "apexPage") {
                this.apexPage = value;

            } else if (name == "enabled") {
                this.enabled = Boolean.valueOf(value);
            }
        }
    }

    @Override
    public Element getElement(ElementBuilder builder) {
        return builder.createElement(getNodeName())
                .addChild("apexPage", apexPage)
                .addChild("enabled", enabled)
                .getElement();
    }

    @Override
    protected String getNodeName() {
        return "pageAccesses";
    }

    @Override
    protected String getMetadataName() {
        return apexPage;
    }
}
