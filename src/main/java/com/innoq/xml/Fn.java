package com.innoq.xml;

public interface Fn<T1, T2> {
  T2 call(T1 arg);
}
