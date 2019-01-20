package sfdc.profiles.profileNodes;

import org.w3c.dom.Element;
import org.w3c.dom.Node;

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

        if (name == "fieldPermissions") return new FieldPermission(node);
        if (name == "classAccesses") return new ClassAccess(node);
        if (name == "applicationVisibilities") return new ApplicationVisibility(node);
        if (name == "layoutAssignments") return new LayoutAssignment(node);
        if (name == "objectPermissions") return new ObjectPermission(node);
        if (name == "pageAccesses") return new PageAccess(node);
        if (name == "recordTypeVisibilities") return new RecordTypeVisibility(node);
        if (name == "tabVisibilities") return new TabVisibility(node);
        if (name == "userLicense") return new UserLicense(node);
        if (name == "userPermissions") return new UserPermission(node);
        if (name == "custom") return new Custom(node);
        if (name == "description") return new Description(node);

        return null;
    }
}
