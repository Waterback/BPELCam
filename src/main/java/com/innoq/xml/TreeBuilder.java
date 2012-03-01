package com.innoq.xml;

import org.jdom.Document;
import org.jdom.Element;

public class TreeBuilder {
  
  Document root;
  Element  currentNode;
  
  
  public TreeBuilder() {
    root=new Document();
    currentNode=null;
  }
  public Element getCurrentNode() {
    return currentNode;
  }
  
  public Element getRoot() {
    return root.getRootElement();
  }
  public Element add(Element e) {
    if(e==null) return currentNode;
    if(currentNode==null) root.setRootElement(e);
    else currentNode.addContent(e);
    currentNode=e;
    return e;
  }
}
