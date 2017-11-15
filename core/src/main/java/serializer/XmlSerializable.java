package serializer;

import org.xml.sax.SAXException;
import structure.Mnode;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.IOException;

public interface XmlSerializable {

    void write(Mnode mnode) throws ParserConfigurationException, TransformerException;

    Mnode read() throws ParserConfigurationException, IOException, SAXException;
    }

