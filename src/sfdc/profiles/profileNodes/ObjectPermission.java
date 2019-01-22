package sfdc.profiles.profileNodes;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.util.Map;

public class ObjectPermission extends ProfileNode {
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
        return builder.createElement(getNodeName())
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
    protected String getNodeName() {
        return "objectPermissions";
    }

    @Override
    protected String getMetadataName() {
        return sObject;
    }
}
