package structure;

import javafx.util.BuilderFactory;
import org.w3c.dom.*;
import org.w3c.dom.CharacterData;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.IOException;

import static org.w3c.dom.Node.*;

public class test {
    public static void main(String[] args) throws ParserConfigurationException, IOException, SAXException, TransformerException {

        Mnode m;
        XmlSerializer x = new XmlSerializer("C:\\Users\\vvans\\IdeaProjects\\app\\core\\file.xml");

        m=x.read();
       XmlSerializer xs = new XmlSerializer("C:\\Users\\vvans\\IdeaProjects\\app\\core\\file2.xml");
       xs.write(m);








    }




}





