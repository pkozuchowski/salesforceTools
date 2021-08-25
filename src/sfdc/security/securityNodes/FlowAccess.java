package sfdc.security.securityNodes;

import org.w3c.dom.Element;

import java.util.Map;

public class FlowAccess extends MetadataNode {
    public String flow;
    public Boolean enabled;
    

    @Override
    protected void initialize(Map<String, String> nodeValues) {
        this.flow = nodeValues.get("flow");
        this.enabled = Boolean.valueOf(nodeValues.get("enabled"));
    }

    @Override
    public Element getElement(ElementBuilder builder) {
        return builder.createElement(getType())
                .addChild("enabled", enabled)
                .addChild("flow", flow)
                .getElement();
    }

    @Override
    public String getType() {
        return "flowAccesses";
    }

    @Override
    public String getApiName() {
        return flow;
    }
}
