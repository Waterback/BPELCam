package com.innoq.xml;

import org.jdom.Element;

public interface ElementHandler {

  void postDepthFstAction(Element e, TreeBuilder ctxt);
  boolean handleElement(Element e, TreeBuilder ctxt);
  
  
}
