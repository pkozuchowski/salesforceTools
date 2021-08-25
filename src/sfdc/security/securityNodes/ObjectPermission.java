package sfdc.security.securityNodes;

import org.w3c.dom.Element;

import java.util.Map;

public class ObjectPermission extends MetadataNode {
    private Boolean
            allowCreate,
            allowDelete,
            allowEdit,
            allowRead,
            modifyAllRecords,
            viewAllRecords;
    private String sObject;

    protected ObjectPermission() {
    }

    public ObjectPermission(String sObject) {
        this(sObject, false, false, false, false, false, false);
    }

    public ObjectPermission(String sObject, Boolean allowCreate, Boolean allowDelete, Boolean allowEdit, Boolean allowRead, Boolean modifyAllRecords, Boolean viewAllRecords) {
        this.allowCreate = allowCreate;
        this.allowDelete = allowDelete;
        this.allowEdit = allowEdit;
        this.allowRead = allowRead;
        this.modifyAllRecords = modifyAllRecords;
        this.viewAllRecords = viewAllRecords;
        this.sObject = sObject;
    }

    @Override
    protected void initialize(Map<String, String> nodeValues) {
        this.sObject = nodeValues.get("object");
        this.allowCreate = Boolean.valueOf(nodeValues.get("allowCreate"));
        this.allowDelete = Boolean.valueOf(nodeValues.get("allowDelete"));
        this.allowEdit = Boolean.valueOf(nodeValues.get("allowEdit"));
        this.allowRead = Boolean.valueOf(nodeValues.get("allowRead"));
        this.modifyAllRecords = Boolean.valueOf(nodeValues.get("modifyAllRecords"));
        this.viewAllRecords = Boolean.valueOf(nodeValues.get("viewAllRecords"));
    }

    @Override
    public Element getElement(ElementBuilder builder) {
        return builder.createElement(getType())
                .addChild("allowCreate", allowCreate)
                .addChild("allowDelete", allowDelete)
                .addChild("allowEdit", allowEdit)
                .addChild("allowRead", allowRead)
                .addChild("modifyAllRecords", modifyAllRecords)
                .addChild("object", sObject)
                .addChild("viewAllRecords", viewAllRecords)
                .getElement();
    }

    @Override
    public String getType() {
        return "objectPermissions";
    }

    @Override
    public String getApiName() {
        return sObject;
    }
}
