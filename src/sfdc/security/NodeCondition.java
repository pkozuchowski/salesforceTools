package sfdc.security;

import sfdc.security.securityNodes.MetadataNode;

public interface NodeCondition {
    boolean isTrue(MetadataNode node);
}
