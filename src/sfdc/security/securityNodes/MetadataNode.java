package sfdc.security.securityNodes;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.util.HashMap;
import java.util.Map;

public abstract class MetadataNode implements Comparable<MetadataNode> {

    @Override
    public int compareTo(MetadataNode o) {
        if (this.getType() == o.getType()) {
            return this.getApiName().compareTo(o.getApiName());
        } else {
            return this.getType().compareTo(o.getType());
        }
    }


    protected abstract void initialize(Map<String, String> nodeValues);
    public abstract Element getElement(ElementBuilder builder);
    public abstract String getType();
    public abstract String getApiName();

    @Override
    public int hashCode() {
        return (getType() + getApiName()).hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof MetadataNode) {
            MetadataNode other = (MetadataNode) obj;
            return (getType() + getApiName()).equals(other.getType() + other.getApiName());
        } else {
            return false;
        }
    }

    public static MetadataNode newInstance(Node node) {
        String name = node.getNodeName();

        MetadataNode metadataNode = null;
        if (name == "fieldPermissions") metadataNode = new FieldPermission();
        if (name == "classAccesses") metadataNode = new ClassAccess();
        if (name == "applicationVisibilities") metadataNode = new ApplicationVisibility();
        if (name == "layoutAssignments") metadataNode = new LayoutAssignment();
        if (name == "objectPermissions") metadataNode = new ObjectPermission();
        if (name == "pageAccesses") metadataNode = new PageAccess();
        if (name == "recordTypeVisibilities") metadataNode = new RecordTypeVisibility();
        if (name == "tabVisibilities") metadataNode = new TabVisibility();
        if (name == "userLicense") metadataNode = new SimpleNode("userLicense");
        if (name == "userPermissions") metadataNode = new UserPermission();
        if (name == "custom") metadataNode = new Custom();
        if (name == "description") metadataNode = new Description();
        if (name == "flowAccesses") metadataNode = new FlowAccess();
        if (name == "customPermissions") metadataNode = new CustomPermission();
        if (name == "hasActivationRequired") metadataNode = new SimpleNode("hasActivationRequired");
        if (name == "label") metadataNode = new SimpleNode("label");
        if (name == "tabSettings") metadataNode = new TabSetting();

        if (metadataNode != null) {
            metadataNode.initialize(getNodeValues(node));
        }

        return metadataNode;
    }

    private static Map<String, String> getNodeValues(Node node) {
        NodeList childNodes = node.getChildNodes();

        Map<String, String> values = new HashMap<>();
        values.put("textContent", node.getTextContent());

        for (int i = 0; i < childNodes.getLength(); i++) {
            Node childNode = childNodes.item(i);
            String name = childNode.getNodeName(),
                    value = childNode.getTextContent();

            values.put(name, value);
        }

        return values;
    }
}
