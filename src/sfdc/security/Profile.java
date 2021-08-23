package sfdc.security;

import org.apache.commons.lang3.StringUtils;
import sfdc.security.securityNodes.MetadataNode;

public class Profile extends MetadataFile {
    private String name;

    public Profile(String path) {
        super(path);
        this.name = StringUtils.substringAfterLast(StringUtils.substringBefore(path, ".profile"), "\\");
    }

    public String getName() {
        return name;
    }

    @Override
    protected String getRootElementName() {
        return "Profile";
    }
}
