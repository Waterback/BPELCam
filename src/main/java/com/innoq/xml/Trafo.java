package com.innoq.xml;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jdom.Attribute;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.Namespace;
import org.jdom.input.SAXBuilder;
import org.jdom.output.XMLOutputter;

public class Trafo {

  public static Element parse(String txt) throws JDOMException, IOException {
    org.jdom.input.SAXBuilder b=new SAXBuilder();
    return b.build(new java.io.StringReader(txt)).getRootElement();
  }
  
  public static Element shallowCopy(Element e) {
    Element ee=new Element(e.getName(), e.getNamespace());
    ArrayList<Attribute> clones=new ArrayList<Attribute>();
    for(Attribute a:(List<Attribute>)e.getAttributes())
      clones.add((Attribute)a.clone());
    ee.setAttributes(clones);
    ee.setText(e.getText());
    return ee;
  }

  
  public static String toString(Element e) {
    org.jdom.output.XMLOutputter out=new XMLOutputter();
    return out.outputString(e);
  }

  
 
  public static class Cloner implements ElementHandler {

    @Override
    public void postDepthFstAction(Element e, TreeBuilder ctxt) {
      //Does nothing
    }

    @Override
    public boolean handleElement(Element e, TreeBuilder ctxt) {
      ctxt.add(shallowCopy(e));
      return false;
    }
  }
  
  public static class IPSplitter implements ElementHandler {

    public static void splitIP(Element prnt, Element ip){
        String[] segments=ip.getText().split("\\.");
        for(String s:segments) {
          Element se=new Element("segment");
          se.setText(s);
          prnt.addContent(se);
        }
      }
    
    
    @Override
    public void postDepthFstAction(Element e, TreeBuilder ctxt) {
      //Does nothing
    }

    @Override
    public boolean handleElement(Element e, TreeBuilder ctxt) {
      if(e.getName()=="ip") {
        splitIP(ctxt.getCurrentNode(), e);
        return true; // No decend in IP node's children
      } else ctxt.add(shallowCopy(e));
      return false;
    }
  }
  
  public static Map<String, Namespace> namespaces(String[] ... prefix_URNs) {
    HashMap<String, Namespace> ns=new HashMap<String, Namespace>();
    for(String[] pu : prefix_URNs)
      ns.put(pu[0], Namespace.getNamespace(pu[0], pu[1]));
    return ns;
  }
  
  public static class ReplaceNS extends IPSplitter {

    Map<String, Namespace> prefixMap;
    HashMap<Namespace, Namespace>  nsMap;
    public ReplaceNS(Map<String, Namespace> prefixMap, String[] ... prefixOldNew) {
      this.prefixMap=prefixMap;
      nsMap=new HashMap<Namespace, Namespace>();
      for(String[] oldNew:prefixOldNew) {
        Namespace nOld=prefixMap.get(oldNew[0]);
        if(nOld!=null) {
          Namespace nNew=prefixMap.get(oldNew[1]);
          nsMap.put(nOld, nNew);
        }
      }
    }
    
    public Element replaceNs(Element arg) {
      Namespace ns=arg.getNamespace();
      if(nsMap.containsKey(ns)) arg.setNamespace(nsMap.get(ns));
      return arg;
    }

    @Override
    public boolean handleElement(Element e, TreeBuilder ctxt) {
      if(e.getName()=="ip") {
        splitIP(ctxt.getCurrentNode(), e);
        return true; // No decend in IP node's children
      } else ctxt.add(replaceNs(shallowCopy(e)));
      return false;
    }

  }
  
  public static String[] ns(String prefix, String urn) {
    return new String[]{prefix, urn};
  }

  public static String[] repl(String prefixO, String prefixN) {
    return new String[]{prefixO, prefixN};
  }

  public static void main(String ... args) {
    try {
      Element sample=parse("<foo:Sample xmlns:foo=\"ratz.fatz.org\" xmlns:bar=\"fizz.buzz.com\" type=\"sample\"><bar:Greetings>Hallo Martin</bar:Greetings><text>ich glaube, mit jdom ist es einfacher, die</text>"
                           +"<ip>1.2.3.4</ip><text>und</text><ip>a.b.c</ip><text> zu ersetzen "
                           +"als mit XSLT</text> </foo:Sample>");
       TreeBuilder t=new TreeBuilder();
       
       ReplaceNS replacer=new ReplaceNS(namespaces(ns("foo", "ratz.fatz.org"),
                                                   ns("bar", "fizz.buzz.com"),
                                                   ns("inno", "innoq.com")),
                                                   repl("foo", "inno"), repl("bar", "foo"));
       Algos.walkDepthFirst(sample, replacer, t);
      System.out.println(toString(t.getRoot()));
    } catch (JDOMException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    
  }
  
}


