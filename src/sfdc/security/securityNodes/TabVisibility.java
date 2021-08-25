package sfdc.security.securityNodes;

import org.w3c.dom.Element;

import java.util.Map;

public class TabVisibility extends MetadataNode {
    public final static String
            DefaultOn = "DefaultOn",
            DefaultOff = "DefaultOff",
            Hidden = "Hidden";

    private String tab;
    private String visibility;

    protected TabVisibility() {
    }

    public TabVisibility(String tab) {
        this(tab, Hidden);
    }

    public TabVisibility(String tab, String visibility) {
        this.tab = tab;
        this.visibility = visibility;
    }

    @Override
    protected void initialize(Map<String, String> nodeValues) {
        this.tab = nodeValues.get("tab");
        this.visibility = nodeValues.get("visibility");
    }

    @Override
    public Element getElement(ElementBuilder builder) {
        return builder.createElement(getType())
                .addChild("tab", tab)
                .addChild("visibility", visibility)
                .getElement();
    }

    @Override
    public String getType() {
        return "tabVisibilities";
    }

    @Override
    public String getApiName() {
        return tab;
    }
}
