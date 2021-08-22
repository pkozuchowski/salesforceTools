package sfdc.security.securityNodes;

import org.w3c.dom.Element;

import java.util.Map;

public class ClassAccess extends MetadataNode {
    private String apexClass;
    private Boolean enabled;

    protected ClassAccess() {
    }

    public ClassAccess(String apexClass) {
        this(apexClass, false);
    }

    public ClassAccess(String apexClass, Boolean enabled) {
        this.apexClass = apexClass;
        this.enabled = enabled;
    }

    @Override
    protected void initialize(Map<String, String> nodeValues) {
        this.apexClass = nodeValues.get("apexClass");
        this.enabled = Boolean.valueOf(nodeValues.get("enabled"));
    }

    @Override
    public Element getElement(ElementBuilder builder) {
        return builder.createElement(getNodeName())
                .addChild("apexClass", apexClass)
                .addChild("enabled", enabled)
                .getElement();
    }

    @Override
    public String getNodeName() {
        return "classAccesses";
    }

    @Override
    public String getMetadataName() {
        return apexClass;
    }
}
