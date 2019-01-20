package sfdc.profiles.profileNodes;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class ApplicationVisibility extends ProfileNode {
    private String application;
    private Boolean isDefault;
    private Boolean visible;

    public ApplicationVisibility(String application) {
        this(application, false, false);
    }

    public ApplicationVisibility(String application, Boolean isDefault, Boolean visible) {
        this.application = application;
        this.isDefault = isDefault;
        this.visible = visible;
    }

    public ApplicationVisibility(Node node) {
        NodeList childNodes = node.getChildNodes();

        for (int i = 0; i < childNodes.getLength(); i++) {
            Node childNode = childNodes.item(i);
            String name = childNode.getNodeName(),
                    value = childNode.getTextContent();

            if (name == "default") {
                this.isDefault = Boolean.valueOf(value);

            } else if (name == "visible") {
                this.visible = Boolean.valueOf(value);

            } else if (name == "application") {
                this.application = value;
            }
        }
    }

    @Override
    public Element getElement(ElementBuilder builder) {
        return builder.createElement(getNodeName())
                .addChild("application", application)
                .addChild("default", isDefault)
                .addChild("visible", visible)
                .getElement();
    }

    @Override
    protected String getNodeName() {
        return "applicationVisibilities";
    }

    @Override
    protected String getMetadataName() {
        return application;
    }
}
