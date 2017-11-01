package serializer;

import org.xml.sax.SAXException;
import structure.Mnode;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

public interface XmlSerializable {

    void write() throws ParserConfigurationException;

    Mnode read() throws ParserConfigurationException, IOException, SAXException;
    }

