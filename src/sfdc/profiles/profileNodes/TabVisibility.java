package sfdc.profiles.profileNodes;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class TabVisibility extends ProfileNode {
    private String tab;
    private String visibility;

    public TabVisibility(String tab) {
        this(tab, Visibility.Hidden);
    }

    public TabVisibility(String tab, Visibility visibility) {
        this.tab = tab;
        this.visibility = visibility.name();
    }

    public TabVisibility(Node node) {
        NodeList childNodes = node.getChildNodes();

        for (int i = 0; i < childNodes.getLength(); i++) {
            Node childNode = childNodes.item(i);
            String name = childNode.getNodeName(),
                    value = childNode.getTextContent();

            if (name == "tab") {
                this.tab = value;

            } else if (name == "visibility") {
                this.visibility = value;
            }
        }
    }

    @Override
    public Element getElement(ElementBuilder builder) {
        return builder.createElement(getNodeName())
                .addChild("tab", tab)
                .addChild("visibility", visibility)
                .getElement();
    }

    @Override
    protected String getNodeName() {
        return "tabVisibilities";
    }

    @Override
    protected String getMetadataName() {
        return tab;
    }

    public enum Visibility {
        DefaultOn,
        DefaultOff,
        Hidden
    }
}
