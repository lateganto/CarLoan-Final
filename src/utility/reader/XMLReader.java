package utility.reader;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.net.URL;

/**
 * Created by salvatore on 08/10/15.
 */
public class XMLReader {

    private Document document;

    public static final String QUERYLIST_PATH = "/integration/dao/QueryList.xml";
    public static final String SERVICELIST_PATH = "/business/applicationservice/ServiceList.xml";
    public static final String BOUNDARYLIST_PATH = "/presentation/boundary/BoundaryList.xml";

    private static XMLReader queryReader = null;
    private static XMLReader serviceReader = null;
    private static XMLReader boundaryReader = null;

    private XMLReader(String path) {

        URL url = getClass().getResource(path);
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();

        try {
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            document = documentBuilder.parse(url.openStream());
        } catch (ParserConfigurationException e) {

            //TODO gestire eccezione
            e.printStackTrace();

        } catch (SAXException e) {

            //TODO gestire eccezione
            e.printStackTrace();
        } catch (IOException e) {

            //TODO gestire eccezione
            e.printStackTrace();
        }

    }

    public String getServiceValue(String id, int request) {

        String result = null;

        document.getDocumentElement().normalize();
        NodeList nodeList = document.getElementsByTagName("service");

        for (int i = 0; i < nodeList.getLength(); i++) {

            Node node = nodeList.item(i);

            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element element = (Element) node;

                if (element.getAttribute("id").matches(id)) {
                    if (request == 0) {
                        result = getTagValue("class", element);
                    } else /*if (request == 1)*/ {
                        result = getTagValue("method", element);
                    }
                }
            }
        }

        return result;
    }

    public String getQueryValue(String id, String typeTag) {

        String result = null;

        document.getDocumentElement().normalize();
        NodeList nodeList = document.getElementsByTagName("query");

        for (int i = 0; i < nodeList.getLength(); i++) {

            Node node = nodeList.item(i);

            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element element = (Element) node;

                if (element.getAttribute("id").matches(id)) {
                    result = getTagValue(typeTag, element);
                }
            }
        }

        return result;

    }

    public String getBoundaryValue(String id) {
        String result = null;

        document.getDocumentElement().normalize();
        NodeList nodeList = document.getElementsByTagName("boundary");

        for (int i = 0; i < nodeList.getLength(); i++) {

            Node node = nodeList.item(i);

            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element element = (Element) node;

                if (element.getAttribute("id").matches(id)) {
                    result = node.getFirstChild().getNodeValue();
                }
            }
        }

        return result;

    }

    public String getRegexValue() {

        document.getDocumentElement().normalize();
        NodeList nodeList = document.getElementsByTagName("regex");

        Node node = nodeList.item(0);

        return node.getFirstChild().getNodeValue();
    }


    private String getTagValue(String tag, Element element) {
        NodeList nodeList = element.getElementsByTagName(tag).item(0).getChildNodes();

        Node node = nodeList.item(0);
        return node.getNodeValue();
    }

    public static XMLReader getReader(String request) {
        switch (request) {
            case "query":
                if (queryReader == null) {
                    queryReader = new XMLReader(QUERYLIST_PATH);
                }

                return queryReader;

            case "service":
                if (serviceReader == null) {
                    serviceReader = new XMLReader(SERVICELIST_PATH);
                }

                return serviceReader;

            case "boundary":
                if (boundaryReader == null) {
                    boundaryReader = new XMLReader(BOUNDARYLIST_PATH);
                }

                return boundaryReader;
        }

        return null;
    }

    /*
    public String getMethodName(String id) {

        String result = null;

        document.getDocumentElement().normalize();
        NodeList nodeList = document.getElementsByTagName(SERVICE_TAG);

        for (int i = 0; i < nodeList.getLength(); i++) {

            Node node = nodeList.item(i);

            if (node.getNodeType() == Node.ATTRIBUTE_NODE) {
                Element element = (Element) node;

                if (element.getAttribute(SERVICE_ID).matches(id)) {
                    result = getTagValue(SERVICE_METHOD_TAG, element);
                }
            }
        }

        return result;
    }

    */





    public static void main(String args[]) {
        XMLReader xmlReader = new XMLReader(XMLReader.SERVICELIST_PATH);
        System.out.print(xmlReader.getServiceValue("login", 1));

        XMLReader xmlReader1 = new XMLReader(XMLReader.QUERYLIST_PATH);
        System.out.print(xmlReader1.getQueryValue("utente", "insert"));

        XMLReader xmlReader2 = new XMLReader(XMLReader.BOUNDARYLIST_PATH);
        System.out.print(xmlReader1.getRegexValue());

        System.out.print(xmlReader2.getBoundaryValue("showLogin"));
    }

    /*





    private Document doc;

    /**
     * Legge il file XML situato nel percorso specificato.
     *
     * @param percorsoFile
     *            percorso del file XML da leggere.
     */
    /*public XMLReader(String percorsoFile) {
        try {
            URL url = getClass().getResource(percorsoFile);
            DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory
                    .newInstance();
            DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
            doc = docBuilder.parse(url.openStream());
        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();

            gestisciEccezione(e);
        }
    }

    /**
     * Restituisce l'attributo di un tag presente in un file XML.
     *
     * @param tag
     *            - tag su cui si vuole effettuare la ricerca.
     * @param id
     *            - attributo che identifica il tag.
     * @param key
     *            - chiave con cui matchare id.
     * @param attribute
     *            attributo di cui si vuole leggere il valore.
     * @return valore dell'attributo cercato.
     */

/*
    private String readElement(String tag, String id, String key,
                               String attribute) {
        String value = "";
        try {
            doc.getDocumentElement().normalize();
            NodeList nList = doc.getElementsByTagName(tag);
            for (int temp = 0; temp < nList.getLength(); temp++) {

                Node nNode = nList.item(temp);
                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;

                    if (eElement.getAttribute(id).matches(key)) {
                        value = eElement.getAttribute(attribute);
                    }
                }
            }
        } catch (Exception e) {

            gestisciEccezione(e);
        }
        return value;
    }
*/
    /**
     * Restituisce una lista contenente tutti i possibili valori che un
     * attributo di un tag assume.
     *
     * @param tag
     *            - tag su cui effettuare la ricerca.
     * @param attribute
     *            - attributo di cui si vuole leggere il valore.
     * @return lista dei possibili valori dell'attributo.
     */
            /*
    public ArrayList<String> readElements(String tag, String attribute) {
        ArrayList<String> elements = new ArrayList<String>();
        try {
            doc.getDocumentElement().normalize();

            NodeList nList = doc.getElementsByTagName(tag);
            for (int temp = 0; temp < nList.getLength(); temp++) {

                Node nNode = nList.item(temp);
                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;
                    elements.add(eElement.getAttribute(attribute));
                }
            }
        } catch (Exception e) {

            gestisciEccezione(e);
        }

        return elements;
    }
    */


    /**
     *Legge i valori dei parametri class e method di un tag call.
     *
     * @param id
     *            - id del nodo da cercare.
     * @return valori dei tag.
     */
/*
    public ArrayList<String> leggiParametri(String id) {
        ArrayList<String> elementi = new ArrayList<String>();
        try {
            doc.getDocumentElement().normalize();
            NodeList nList = doc.getElementsByTagName("call");
            for (int temp = 0; temp < nList.getLength(); temp++) {

                Node nNode = nList.item(temp);
                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;
                    // TROVARE UN MODO PER VERIFICARE CHE L'ATTRIBUTO CHE LEGGO
                    // SIA UGUALE AL VALORE key PASSATO AL METODO

                    if (eElement.getAttribute("id").matches(id)) {
                        elementi.add(getTagValue("class", eElement));
                        elementi.add(getTagValue("method", eElement));
                    }
                }
            }
        } catch (Exception e) {

            gestisciEccezione(e);
        }
        return elementi;
    }
    */

    /**
     * Permette di ottenere il valore di un tag appartenente ad un nodo.
     *
     * @param tag
     *            - tag da cui prelevare il valore.
     * @param element
     *            - nodo a cui appartiene il tag.
     * @return valore del tag.
     */

}
