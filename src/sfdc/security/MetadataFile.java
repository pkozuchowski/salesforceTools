package sfdc.security;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import sfdc.security.securityNodes.ElementBuilder;
import sfdc.security.securityNodes.MetadataNode;

import javax.swing.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.FileWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;

public abstract class MetadataFile {
    protected String            path;
    protected Set<MetadataNode> metadataNodes = new HashSet<>();

    public MetadataFile(String path) {
        this.path = path;
        parseFile();
    }

    /**
     * Adds or replaces existing permission node in the Profile xml.
     *
     * @param node - Concrete instance of ProfileNode, which may be ex. FieldPermission node.
     */
    public void add(MetadataNode node) {
        this.metadataNodes.remove(node);
        this.metadataNodes.add(node);
    }

    public Set<MetadataNode> getMetadataNodes() {
        return metadataNodes;
    }

    public void remove(NodeCondition condition) {
        Set<MetadataNode> filteredNodes = new HashSet<>();

        for (MetadataNode node : this.metadataNodes) {
            if (!condition.isTrue(node)) {
                filteredNodes.add(node);
            }
        }

        this.metadataNodes = filteredNodes;
    }

    /**
     * Removes existing permission node in the Profile xml.
     *
     * @param node - Concrete instance of ProfileNode, which may be ex. FieldPermission node.
     */
    public void remove(MetadataNode node) {
        this.metadataNodes.remove(node);
    }


    public void merge(MetadataFile other) {
        this.metadataNodes.addAll(other.metadataNodes);
    }

    public void merge(String path) {
        Profile other = new Profile(path);
        this.merge(other);
    }

    public void retainSamePermissions(MetadataFile other) {
        Set<MetadataNode> filteredNodes = new HashSet<>();

        for (MetadataNode metadataNode : metadataNodes) {
            if (other.metadataNodes.contains(metadataNode)) {
                filteredNodes.add(metadataNode);
            }
        }

        this.metadataNodes = filteredNodes;
    }

    public void saveFile() {
        try {
            DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document document = builder.newDocument();
            document.setXmlStandalone(true);

            Element rootElement = document.createElementNS("http://soap.sforce.com/2006/04/metadata", getRootElementName());
            document.appendChild(rootElement);

            ElementBuilder elementBuilder = new ElementBuilder(document);

            ArrayList<MetadataNode> metadataNodes = new ArrayList<>(this.metadataNodes);
            metadataNodes.sort(Comparator.naturalOrder());

            for (MetadataNode node : metadataNodes) {
                rootElement.appendChild(node.getElement(elementBuilder));
            }


            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty(OutputKeys.METHOD, "xml");
            transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");

            StringWriter writer = new StringWriter();
            transformer.transform(
                    new DOMSource(document),
                    new StreamResult(writer)
            );

            String xml = writer.toString().replace("<?xml version=\"1.0\" encoding=\"UTF-8\"?>", "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
            FileWriter myWriter = new FileWriter(path);
            myWriter.write(xml);
            myWriter.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void parseFile() {
        try {
            DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document document = builder.parse(new File(path));

            NodeList childNodes = document.getDocumentElement().getChildNodes();

            for (int i = 0; i < childNodes.getLength(); i++) {
                MetadataNode metadataNode = MetadataNode.newInstance(childNodes.item(i));

                if (metadataNode != null) {
                    this.metadataNodes.add(metadataNode);
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    protected abstract String getRootElementName();
}
