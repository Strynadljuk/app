package structure;


import java.io.Serializable;
import java.util.*;


public class Mnode implements Serializable {

    public int type=0;
    public Mnode parent=null;
    private String tagName = "";
    private String value = "";
    private Map<String, String> attributes=new HashMap<String, String>();
    List<Mnode> childMnodes=new ArrayList<>();
    Mnode(){}
    Mnode (String name){
        this.tagName=name;
    }

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

    public  void printMnode(Mnode mnode) {



           if(mnode.type==0){
               System.out.println("<?xml version=\"1.0\"?>");
           }
           if(!mnode.tagName.equals("")){
            System.out.print("<" + mnode.getTagName());
            Map<String, String> nm = mnode.getAttributes();
            for (int i = 0; i < nm.size(); i++) {
                Iterator<String> itr = nm.keySet().iterator();
                Iterator<String> itr2 = nm.values().iterator();
                System.out.print(" " + itr.next() + "=" + itr2.next() + " ");
            }
            System.out.print(">");}


            if (mnode.getChildMnodes().size() == 0) {
                System.out.print(mnode.getValue());
                System.out.print("</"+mnode.getTagName()+">");}

        List<Mnode> list = mnode.getChildMnodes();
            for (int i = 0; i < list.size(); i++){

                printMnode(list.get(i));
                if(mnode.getTagName()!=""){
                System.out.println("</"+mnode.getTagName()+">");}}
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
