package com.innoq.xml;

import org.jdom.Element;

public interface Predicate {

  boolean call(Element e);

}
