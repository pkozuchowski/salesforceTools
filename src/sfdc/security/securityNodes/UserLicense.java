package sfdc.security.securityNodes;

import org.w3c.dom.Element;

import java.util.Map;

public class UserLicense extends MetadataNode {
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
    public String getNodeName() {
        return "userLicense";
    }

    @Override
    public String getMetadataName() {
        return "";
    }
}
