package structure;


import java.io.Serializable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;


public class Mnode implements Serializable {

    public Mnode parent;
    private String tagName = "";
    private String value = "";
    private Map<String, String> attributes;
    private List<Mnode> childMnodes;

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        if (tagName == null) {
            throw new NullPointerException("properties");
        }
        this.tagName = tagName;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        if (value == null) {
            throw new NullPointerException("properties");
        }
        this.value = value;
    }

    public Map<String, String> getAttributes() {
        return attributes;
    }

    public void setAttributes(Map<String, String> attributes) {
        this.attributes = attributes;
    }

    public List<Mnode> getChildMnodes() {
        return childMnodes;
    }

    public void setChildMnodes(List<Mnode> childMnodes) {
        this.childMnodes = childMnodes;
    }

    //*control mhetods/

    public void addMnode(Mnode mnode) {
        mnode.parent = this;
        this.childMnodes.add(mnode);
    }


    public void remove(Mnode seachMnode){
        removeMnode(this,seachMnode);
    }
    private void removeMnode(Mnode mnode,Mnode seachMnode) {
        if(mnode==null){return;}
        if(mnode.equals(seachMnode)){
            List<Mnode> list = mnode.parent.childMnodes;
            list.remove(mnode);
        }
        for (int i = 0; i <mnode.childMnodes.size() ; i++) {
            removeMnode(mnode.childMnodes.get(i),seachMnode);
        }
    }

    public void print(Mnode mnode){
        if(mnode==null){return;}
        if(!mnode.tagName.equals("#text")&&!mnode.tagName.equals("#cdata")){
            System.out.print("<");
            System.out.print(mnode.tagName);
            if(!mnode.attributes.isEmpty()){
                Iterator<String> key=mnode.attributes.keySet().iterator();
                Iterator<String> values=mnode.attributes.values().iterator();
                while(key.hasNext()){
                    System.out.print(" "+key.next()+"=\""+values.next()+"\" ");
                }
                System.out.println(">");
            }
            if(!mnode.childMnodes.isEmpty()){
                List<Mnode> mnodeList=mnode.childMnodes;
                for (int i = 0; i <mnodeList.size() ; i++) {
                    print(mnodeList.get(i));
                }
            }
            System.out.print("</"+mnode.tagName+">");
        }
        if(mnode.tagName.equals("#text")){
            System.out.print(mnode.value);
        }
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Mnode mnode = (Mnode) o;

        if (tagName != null ? !tagName.equals(mnode.tagName) : mnode.tagName != null) return false;
        if (value != null ? !value.equals(mnode.value) : mnode.value != null) return false;
        if (attributes != null ? !attributes.equals(mnode.attributes) : mnode.attributes != null) return false;
        return childMnodes != null ? childMnodes.equals(mnode.childMnodes) : mnode.childMnodes == null;
    }

    @Override
    public int hashCode() {
        int result = tagName != null ? tagName.hashCode() : 0;
        result = 31 * result + (value != null ? value.hashCode() : 0);
        result = 31 * result + (attributes != null ? attributes.hashCode() : 0);
        result = 31 * result + (childMnodes != null ? childMnodes.hashCode() : 0);
        return result;
    }

}
