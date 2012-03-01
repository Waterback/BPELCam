package com.innoq.xml;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.jdom.Element;

import com.innoq.xml.ElementHandler;
import com.innoq.xml.TreeBuilder;


public class Algos {

  
  public static List<Element> filter(Collection<Element> c, Predicate p) {
    return filter(c, p, new ArrayList<Element>());
  }

  public static List<Element> filter(Collection<Element> c, Predicate p, List<Element> accumulator) {
    for(Element e:c)
      if(p.call(e)) accumulator.add(e);
    return accumulator;
  }

  
  // public List<Element>
  /**
   * @param e
   * @param p
   * @return
   */
  public static List <Element> filterChilren(Element e, Predicate p) {
    return filterChildren(e, p, new ArrayList<Element>());
  }

  /**
   * @param e
   * @param p
   * @param accumulator
   * @return
   */
  @SuppressWarnings("unchecked")
  public static List <Element> filterChildren(Element e, Predicate p, List<Element> accumulator) {
    for(Element c:(List<Element>)e.getChildren())
      if(p.call(c)) accumulator.add(c);
    return accumulator;
  }

  /**
   * @param e
   * @param p
   * @return
   */
  public static List <Element> filterDescendants(Element e, Predicate p) {
    return filterDescendants(e, p, new ArrayList<Element>());
  }
  
  /**
   * @param e
   * @param p
   * @param accumulator
   * @return
   */
  @SuppressWarnings("unchecked")
  public static List <Element> filterDescendants(Element e, Predicate p, List<Element> accumulator) {
    for(Element c:(List<Element>)e.getChildren())
      if(p.call(c)) accumulator.add(c);
      else filterDescendants(c, p, accumulator);
    return accumulator;
  }

  
  
  
  public static Element findParentOrSelf(Element e, Predicate p) {
    Element prnt=e.getParentElement();
    if(prnt==null || p.call(prnt)) return prnt;
    else return findParentOrSelf(prnt, p);
  }

  
  public static Element find(List<Element> elements, Predicate p) {
    for(Element e:elements)
      if(p.call(e)) return e;
    return null;
  }
  
  /**
   * Find the first element x from e's decendants for which p.call(x) is true.
   * @param e an Element
   * @param p a Predicate p.call(Element)->boolean
   * @return
   */
  @SuppressWarnings("unchecked")
  public static Element find(Element e, Predicate p) {
    List<Element> children=e.getChildren();
    Element cc=find(e.getChildren(), p);
    for(Element c:children)
      if(cc!=null) break;
      else cc=find(c, p);
    return cc;
  }
  
  
  public static Element findBreadthFirst(Element element, Predicate p) {
    ArrayList<Element> elements=new ArrayList<Element>();
    elements.add(element);
    return findBreadthFirst(elements, p);
  }
  
  @SuppressWarnings("unchecked")
  public static Element findBreadthFirst(List<Element> elements, Predicate p) {
    List<Element> descent=new ArrayList<Element>();
    for(Element e:elements)
      if(p.call(e))
        return e;
      else
        descent.addAll(e.getChildren());
    if (elements.isEmpty()) return null;
    else return findBreadthFirst(descent, p);
  }

  
  
  /**
   * Walk the element tree of e depth first, invoking element handler h
   * on every element for side effects. A return value of true from the element
   * handler indicates that the handler itself has traversed all descendants
   * and walkDepthFirst will not descend further the children of that element.
   * After all children of Element e have been processed ElementHandler.postDepthFstAction is called.
   * 
   * @param e
   * @param h
   * @param ctxt
   */
  @SuppressWarnings("unchecked")
  public static void walkDepthFirst(Element e, ElementHandler h, TreeBuilder ctxt) {
    if(e==null || h.handleElement(e, ctxt)) return;
    else for(Element c:(List<Element>)e.getChildren())
      walkDepthFirst(c, h, ctxt);
    h.postDepthFstAction(e, ctxt);
  }

   
  public static<Dom, CoDom> List<CoDom> map(Collection<Dom> list, Fn<Dom, CoDom> fn) {
    ArrayList<CoDom> res=new ArrayList<CoDom>();
    for(Dom arg : list)
      try { res.add(fn.call(arg)); } catch(Exception e) {}
    return res;
    
  }

  public static class Pair<T> {
    public Pair(T fst, T snd) { this.fst=fst; this.snd=snd; }
    public T fst;
    public T snd;
  }
  
  public static Pair<List<Element>> partition(Collection<Element> c, Predicate p) {
    Pair<List<Element>> res=new Pair<List<Element>>(new ArrayList<Element>(), new ArrayList<Element>());
    for(Element e:c)
      if (p.call(e)) res.fst.add(e);
      else res.snd.add(e);
    return res;
  }
}
  

