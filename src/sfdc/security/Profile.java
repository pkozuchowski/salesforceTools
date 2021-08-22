package sfdc.security;

public class Profile extends MetadataFile {

    public Profile(String path) {
        super(path);
    }

    @Override
    protected String getRootElementName() {
        return "Profile";
    }
}
