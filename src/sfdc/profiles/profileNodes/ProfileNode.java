package sfdc.profiles.profileNodes;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.util.HashMap;
import java.util.Map;

public abstract class ProfileNode implements Comparable<ProfileNode> {

    @Override
    public int compareTo(ProfileNode o) {
        if (this.getNodeName() == o.getNodeName()) {
            return this.getMetadataName().compareTo(o.getMetadataName());
        } else {
            return this.getNodeName().compareTo(o.getNodeName());
        }
    }


    public abstract Element getElement(ElementBuilder builder);
    protected abstract void initialize(Map<String, String> nodeValues);
    protected abstract String getNodeName();
    protected abstract String getMetadataName();

    @Override
    public int hashCode() {
        return (getNodeName() + getMetadataName()).hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof ProfileNode) {
            ProfileNode other = (ProfileNode) obj;
            return (getNodeName() + getMetadataName()).equals(other.getNodeName() + other.getMetadataName());
        } else {
            return false;
        }
    }

    public static ProfileNode newInstance(Node node) {
        String name = node.getNodeName();

        ProfileNode profileNode = null;
        if (name == "fieldPermissions") profileNode = new FieldPermission();
        if (name == "classAccesses") profileNode = new ClassAccess();
        if (name == "applicationVisibilities") profileNode = new ApplicationVisibility();
        if (name == "layoutAssignments") profileNode = new LayoutAssignment();
        if (name == "objectPermissions") profileNode = new ObjectPermission();
        if (name == "pageAccesses") profileNode = new PageAccess();
        if (name == "recordTypeVisibilities") profileNode = new RecordTypeVisibility();
        if (name == "tabVisibilities") profileNode = new TabVisibility();
        if (name == "userLicense") profileNode = new UserLicense();
        if (name == "userPermissions") profileNode = new UserPermission();
        if (name == "custom") profileNode = new Custom();
        if (name == "description") profileNode = new Description();

        if (profileNode != null) {
            profileNode.initialize(getNodeValues(node));
        }

        return profileNode;
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
