package sfdc.profiles.profileNodes;

import org.w3c.dom.Element;
import org.w3c.dom.Node;

public class UserLicense extends ProfileNode {
    private String license;

    public UserLicense(String license) {
        this.license = license;
    }

    public UserLicense(Node node) {
        this.license = node.getTextContent();
    }

    @Override
    public Element getElement(ElementBuilder builder) {
        return builder.createElement(getNodeName())
                .setTextContent(license)
                .getElement();
    }

    @Override
    protected String getNodeName() {
        return "userLicense";
    }

    @Override
    protected String getMetadataName() {
        return "";
    }
}
