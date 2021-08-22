package sfdc.security;

public class PermissionSet extends MetadataFile {

    public PermissionSet(String path) {
        super(path);
    }

    @Override
    protected String getRootElementName() {
        return "PermissionSet";
    }
}
