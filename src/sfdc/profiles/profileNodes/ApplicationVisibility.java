package sfdc.profiles.profileNodes;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.util.Map;

public class ApplicationVisibility extends ProfileNode {
    private String application;
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
        this.isDefault = Boolean.valueOf(nodeValues.get("default"));
        this.visible = Boolean.valueOf(nodeValues.get("visible"));
    }


    @Override
    public Element getElement(ElementBuilder builder) {
        return builder.createElement(getNodeName())
                .addChild("application", application)
                .addChild("default", isDefault)
                .addChild("visible", visible)
                .getElement();
    }

    @Override
    protected String getNodeName() {
        return "applicationVisibilities";
    }

    @Override
    protected String getMetadataName() {
        return application;
    }
}
