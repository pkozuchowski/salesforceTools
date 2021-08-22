package sfdc.security.securityNodes;

import org.w3c.dom.Element;

import java.util.Map;

public class PageAccess extends MetadataNode {
    private String apexPage;
    private Boolean enabled;

    protected PageAccess() {
    }

    public PageAccess(String apexPage) {
        this(apexPage, false);
    }

    public PageAccess(String apexPage, Boolean enabled) {
        this.apexPage = apexPage;
        this.enabled = enabled;
    }

    @Override
    protected void initialize(Map<String, String> nodeValues) {
        this.apexPage = nodeValues.get("apexPage");
        this.enabled = Boolean.valueOf(nodeValues.get("enabled"));
    }

    @Override
    public Element getElement(ElementBuilder builder) {
        return builder.createElement(getNodeName())
                .addChild("apexPage", apexPage)
                .addChild("enabled", enabled)
                .getElement();
    }

    @Override
    public String getNodeName() {
        return "pageAccesses";
    }

    @Override
    public String getMetadataName() {
        return apexPage;
    }
}
