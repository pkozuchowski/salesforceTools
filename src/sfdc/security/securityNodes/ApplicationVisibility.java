package sfdc.security.securityNodes;

import org.w3c.dom.Element;

import java.util.Map;

public class ApplicationVisibility extends MetadataNode {
    private String  application;
    private Boolean isDefault;
    private Boolean visible;

    protected ApplicationVisibility() {
    }

    public ApplicationVisibility(String application) {
        this(application, false, false);
    }

    public ApplicationVisibility(String application, Boolean isDefault, Boolean visible) {
        this.application = application;
        this.isDefault = isDefault;
        this.visible = visible;
    }

    @Override
    protected void initialize(Map<String, String> nodeValues) {
        this.application = nodeValues.get("application");
        this.visible = Boolean.valueOf(nodeValues.get("visible"));
        if (nodeValues.get("default") != null) {
            this.isDefault = Boolean.valueOf(nodeValues.get("default"));
        }
    }


    @Override
    public Element getElement(ElementBuilder builder) {
        builder.createElement(getType());
        builder.addChild("application", application);
        if (isDefault != null) {
            builder.addChild("default", isDefault);
        }
        builder.addChild("visible", visible);

        return builder.getElement();
    }

    @Override
    public String getType() {
        return "applicationVisibilities";
    }

    @Override
    public String getApiName() {
        return application;
    }
}
