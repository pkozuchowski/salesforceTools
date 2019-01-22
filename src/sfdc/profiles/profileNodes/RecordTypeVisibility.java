package sfdc.profiles.profileNodes;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.util.Map;

public class RecordTypeVisibility extends ProfileNode {
    private String recordType;
    private Boolean
            isDefault,
            visible;

    protected RecordTypeVisibility() {
    }

    public RecordTypeVisibility(String recordType) {
        this(recordType, false, false);
    }

    public RecordTypeVisibility(String recordType, Boolean isDefault, Boolean visible) {
        this.recordType = recordType;
        this.isDefault = isDefault;
        this.visible = visible;
    }

    @Override
    protected void initialize(Map<String, String> nodeValues) {
        this.recordType = nodeValues.get("recordType");
        this.isDefault = Boolean.valueOf(nodeValues.get("default"));
        this.visible = Boolean.valueOf(nodeValues.get("visible"));
    }

    @Override
    public Element getElement(ElementBuilder builder) {
        return builder.createElement(getNodeName())
                .addChild("default", isDefault)
                .addChild("recordType", recordType)
                .addChild("visible", visible)
                .getElement();
    }

    @Override
    protected String getNodeName() {
        return "recordTypeVisibilities";
    }

    @Override
    protected String getMetadataName() {
        return recordType;
    }
}
