package sfdc.security.securityNodes;

import org.w3c.dom.Element;

import java.util.Map;

public class LayoutAssignment extends MetadataNode {
    public String layout;
    public String recordType;

    protected LayoutAssignment() {
    }

    public LayoutAssignment(String layout) {
        this.layout = layout;
    }

    public LayoutAssignment(String layout, String recordType) {
        this.layout = layout;
        this.recordType = recordType;
    }

    @Override
    protected void initialize(Map<String, String> nodeValues) {
        this.layout = nodeValues.get("layout");
        this.recordType = nodeValues.get("recordType");

        if (this.recordType == null) {
            this.recordType = "";
        }
    }

    @Override
    public Element getElement(ElementBuilder builder) {
        builder.createElement(getNodeName())
                .addChild("layout", layout);

        if (recordType != "" && recordType != null) {
            builder.addChild("recordType", recordType);
        }

        return builder.getElement();
    }

    @Override
    public String getNodeName() {
        return "layoutAssignments";
    }

    @Override
    public String getMetadataName() {
        return recordType + layout;
    }

    @Override
    public int compareTo(MetadataNode o) {
        if (o instanceof LayoutAssignment) {
            LayoutAssignment other = (LayoutAssignment) o;

            if (this.layout.equals(other.layout)) {
                return this.recordType.compareTo(other.recordType);
            } else {
                return this.layout.compareTo(other.layout);
            }
        }

        return super.compareTo(o);
    }
}
