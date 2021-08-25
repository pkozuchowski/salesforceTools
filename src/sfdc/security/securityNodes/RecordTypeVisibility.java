package sfdc.security.securityNodes;

import org.w3c.dom.Element;

import java.util.Map;

public class RecordTypeVisibility extends MetadataNode {
    private String  recordType;
    private Boolean isDefault,
            visible,
            personAccountDefault;

    protected RecordTypeVisibility() {
    }

    public RecordTypeVisibility(String recordType) {
        this(recordType, false, false);
    }

    public RecordTypeVisibility(String recordType, Boolean isDefault, Boolean visible) {
        this.recordType = recordType;
        this.isDefault = isDefault;
        this.visible = visible;
        this.personAccountDefault = false;
    }

    @Override
    protected void initialize(Map<String, String> nodeValues) {
        this.recordType = nodeValues.get("recordType");
        this.visible = Boolean.valueOf(nodeValues.get("visible"));
        this.personAccountDefault = Boolean.valueOf(nodeValues.get("personAccountDefault")) == true;

        if (nodeValues.get("default") != null) {
            this.isDefault = Boolean.valueOf(nodeValues.get("default"));
        }
    }

    @Override
    public Element getElement(ElementBuilder builder) {
        builder.createElement(getType());

        if (isDefault != null) {
            builder.addChild("default", isDefault);
        }

        if (personAccountDefault == true) {
            builder.addChild("personAccountDefault", personAccountDefault);
        }

        return builder.addChild("recordType", recordType)
                .addChild("visible", visible)
                .getElement();
    }

    @Override
    public String getType() {
        return "recordTypeVisibilities";
    }

    @Override
    public String getApiName() {
        return recordType;
    }
}
