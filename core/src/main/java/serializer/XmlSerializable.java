package serializer;

import structure.Mnode;

public interface XmlSerializable {

    void write();

    Mnode read();
    }

