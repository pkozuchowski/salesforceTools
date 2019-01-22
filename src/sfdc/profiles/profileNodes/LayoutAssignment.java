package sfdc.profiles.profileNodes;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.util.Map;

public class LayoutAssignment extends ProfileNode {
    private String sObject;
    private String layout;
    private String recordType;

    protected LayoutAssignment() {
    }

    public LayoutAssignment(String layout) {
        this.layout = layout;
    }

    @Override
    protected void initialize(Map<String, String> nodeValues) {
        this.layout = nodeValues.get("layout");
        this.recordType = nodeValues.get("recordType");
        this.sObject = this.layout.substring(0, this.layout.indexOf("-"));
    }

    @Override
    public Element getElement(ElementBuilder builder) {
        builder.createElement(getNodeName())
                .addChild("layout", layout);

        if (recordType != "") {
            builder.addChild("recordType", recordType);
        }

        return builder.getElement();
    }

    @Override
    protected String getNodeName() {
        return "layoutAssignments";
    }

    @Override
    protected String getMetadataName() {
        return sObject + recordType;
    }
}
