package sfdc.security.securityNodes;

import org.w3c.dom.Element;

import java.util.Map;

public class CustomPermission extends MetadataNode {
    public String flow;
    public Boolean enabled;
    

    @Override
    protected void initialize(Map<String, String> nodeValues) {
        this.flow = nodeValues.get("name");
        this.enabled = Boolean.valueOf(nodeValues.get("enabled"));
    }

    @Override
    public Element getElement(ElementBuilder builder) {
        return builder.createElement(getNodeName())
                .addChild("enabled", enabled)
                .addChild("name", flow)
                .getElement();
    }

    @Override
    public String getNodeName() {
        return "customPermissions";
    }

    @Override
    public String getMetadataName() {
        return flow;
    }
}
