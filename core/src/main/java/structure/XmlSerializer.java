package structure;

import org.w3c.dom.*;
import org.xml.sax.Attributes;
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
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class XmlSerializer implements XmlSerializable {

   private File path;
   XmlSerializer(String path){
       this.path= new File(path);
   }

    public void write(Mnode mnode) throws ParserConfigurationException, TransformerException {
        Document document = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();
        writeXml(mnode, document, document);
        saveInFile(document,this.path);


    }

    private void writeXml(Mnode in, Node out, Document doc) {
        if (in == null) {
            return;
        }

            for (int i = 0; i < in.childMnodes.size(); i++) {
                String nodeName=in.childMnodes.get(i).getTagName();
                Element el = doc.createElement(nodeName);
                Mnode mnode = in.getChildMnodes().get(i);

                if (!mnode.getAttributes().isEmpty()) {
                    Iterator<String> iter = mnode.getAttributes().keySet().iterator();
                    Iterator<String> iterT = mnode.getAttributes().values().iterator();
                    while (iter.hasNext()) {
                        el.setAttribute(iter.next(), iterT.next());
                    }
                }
                if (mnode.getChildMnodes().size()==0) {
                    el.setTextContent(mnode.getValue());
                }
                out.appendChild(el);
                writeXml(in.getChildMnodes().get(i), out.getChildNodes().item(i), doc);
            }




    }



    public Mnode read() throws ParserConfigurationException, IOException, SAXException {
       Mnode mnode = new Mnode();
       Document document=DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(this.path);
       readXml(document,mnode);
       return mnode;

    }

    private void readXml(Node in, Mnode out) {
        if (in == null) {
            return;
        }
        for (int i = 0, j = 0; i < in.getChildNodes().getLength(); i++, j++) {
            if (in.getChildNodes().item(i).getNodeType() != 1) {
                j--;
                continue;
            }
            Mnode tr = new Mnode(in.getChildNodes().item(i).getNodeName());
            tr.type=in.getChildNodes().item(i).getNodeType();
            if(in.getChildNodes().item(i).getTextContent()!=null){
                tr.setValue(in.getChildNodes().item(i).getTextContent());
            }
            if(in.getChildNodes().item(i).hasAttributes()){
                for (int k = 0; k < in.getChildNodes().item(i).getAttributes().getLength(); k++) {
                    String key = in.getChildNodes().item(i).getAttributes().item(k).getNodeName();
                    String value = in.getChildNodes().item(i).getAttributes().item(k).getNodeValue();
                    tr.getAttributes().put(key, value);
                }
            }
            out.addMnode(tr);
            readXml(in.getChildNodes().item(i),out.getChildMnodes().get(j));
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
