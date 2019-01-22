package sfdc.profiles.profileNodes;

import org.w3c.dom.Element;

import java.util.Map;

public class FieldPermission extends ProfileNode {
    private Boolean editable, readable;
    private String field;

    protected FieldPermission() {
    }

    public FieldPermission(String field) {
        this(field, false, false);
    }

    public FieldPermission(String field, Boolean editable, Boolean readable) {
        this.field = field;
        this.editable = editable;
        this.readable = readable;
    }

    @Override
    protected void initialize(Map<String, String> nodeValues) {
        this.field = nodeValues.get("field");
        this.editable = Boolean.valueOf(nodeValues.get("editable"));
        this.readable = Boolean.valueOf(nodeValues.get("readable"));
    }

    @Override
    public Element getElement(ElementBuilder builder) {
        return builder.createElement(getNodeName())
                .addChild("editable", editable)
                .addChild("field", field)
                .addChild("readable", readable)
                .getElement();
    }

    @Override
    protected String getNodeName() {
        return "fieldPermissions";
    }

    @Override
    protected String getMetadataName() {
        return field;
    }
}
