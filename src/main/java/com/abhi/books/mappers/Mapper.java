package com.abhi.books.mappers;

public interface Mapper<A, B>  {

    B mapTo(A a);

    A mapFrom(B b);
}
