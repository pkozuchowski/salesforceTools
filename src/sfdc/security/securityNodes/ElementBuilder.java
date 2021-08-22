package sfdc.security.securityNodes;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class ElementBuilder {
    private Document document;
    private Element element;

    public ElementBuilder(Document document) {
        this.document = document;
    }

    public ElementBuilder createElement(String name) {
        this.element = document.createElement(name);
        return this;
    }

    public ElementBuilder addChild(String name, Object value) {
        Element child = document.createElement(name);
        String textContent = ("" + value).trim();
        child.setTextContent(textContent);

        element.appendChild(child);
        return this;
    }

    public ElementBuilder setTextContent(Object value) {
        String textContent = ("" + value).trim();
        element.setTextContent(textContent);
        return this;
    }

    public Element getElement() {
        return element;
    }
}
