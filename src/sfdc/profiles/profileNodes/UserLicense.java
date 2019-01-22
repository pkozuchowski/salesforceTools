package sfdc.profiles.profileNodes;

import org.w3c.dom.Element;
import org.w3c.dom.Node;

import java.util.Map;

public class UserLicense extends ProfileNode {
    private String license;

    protected UserLicense() {
    }

    public UserLicense(String license) {
        this.license = license;
    }

    @Override
    protected void initialize(Map<String, String> nodeValues) {
        this.license = nodeValues.get("textContent");
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
