package sfdc.security.securityNodes;

import org.w3c.dom.Element;

import java.util.Map;

public class UserPermission extends MetadataNode {
    private String name;
    private Boolean enabled;

    protected UserPermission() {
    }

    public UserPermission(String name) {
        this(name, false);
    }

    public UserPermission(String name, Boolean enabled) {
        this.name = name;
        this.enabled = enabled;
    }

    @Override
    protected void initialize(Map<String, String> nodeValues) {
        this.name = nodeValues.get("name");
        this.enabled = Boolean.valueOf(nodeValues.get("enabled"));
    }

    @Override
    public Element getElement(ElementBuilder builder) {
        return builder.createElement(getType())
                .addChild("enabled", enabled)
                .addChild("name", name)
                .getElement();
    }


    @Override
    public String getType() {
        return "userPermissions";
    }

    @Override
    public String getApiName() {
        return name;
    }
}
