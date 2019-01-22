package sfdc.profiles.profileNodes;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.util.Map;

public class UserPermission extends ProfileNode {
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
        return builder.createElement(getNodeName())
                .addChild("enabled", enabled)
                .addChild("name", name)
                .getElement();
    }


    @Override
    protected String getNodeName() {
        return "userPermissions";
    }

    @Override
    protected String getMetadataName() {
        return name;
    }
}
