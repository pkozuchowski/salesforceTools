package sfdc.profiles.profileNodes;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class ObjectPermission extends ProfileNode {
    private Boolean
            allowCreate,
            allowDelete,
            allowEdit,
            allowRead,
            modifyAllRecords,
            viewAllRecords;
    private String sObject;


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

    public ObjectPermission(Node node) {
        NodeList childNodes = node.getChildNodes();

        for (int i = 0; i < childNodes.getLength(); i++) {
            Node childNode = childNodes.item(i);
            String name = childNode.getNodeName(),
                    value = childNode.getTextContent();

            if (name == "allowCreate") {
                this.allowCreate = Boolean.valueOf(value);
            } else if (name == "allowDelete") {
                this.allowDelete = Boolean.valueOf(value);
            } else if (name == "allowEdit") {
                this.allowEdit = Boolean.valueOf(value);
            } else if (name == "allowRead") {
                this.allowRead = Boolean.valueOf(value);
            } else if (name == "modifyAllRecords") {
                this.modifyAllRecords = Boolean.valueOf(value);
            } else if (name == "viewAllRecords") {
                this.viewAllRecords = Boolean.valueOf(value);
            } else if (name == "object") {
                this.sObject = value;
            }
        }
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
