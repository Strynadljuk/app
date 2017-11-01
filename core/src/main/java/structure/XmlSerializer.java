package structure;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;
import serializer.XmlSerializable;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;

public class XmlSerializer implements XmlSerializable {

   private File path;
   XmlSerializer(String path){
       this.path= new File(path);
   }

    public void write() throws ParserConfigurationException {
        Document document = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();


    }



    public Mnode read() throws ParserConfigurationException, IOException, SAXException {
       Mnode mnode = new Mnode();
       Document document=DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(this.path);
       readXml(document,mnode);
       return mnode;

    }

    private void readXml(Node in, Mnode out){
       if(in==null){return;}
       Mnode mnode=new Mnode();
       if(in.getNodeType()==1)

        for (int i = 0; i <in.getChildNodes().getLength() ; i++) {

        }
    }



    // Save Node in XML file
    private  void saveInFile(Document document, File path) throws TransformerException {
        Transformer transformer = TransformerFactory.newInstance().newTransformer();
        DOMSource source = new DOMSource(document);
        StreamResult result = new StreamResult(path);
        transformer.transform(source, result);
        System.out.println("new XML has been saved");
    }
}
