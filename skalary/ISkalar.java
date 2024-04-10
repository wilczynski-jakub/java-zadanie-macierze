package skalary;

public interface ISkalar<T> {

    Skalar<T> suma(Skalar<T> skladnik);
    Skalar<T> iloczyn(Skalar<T> czynnik);
    Skalar<T> bezwzględna();
    Skalar<T> zero();

} //interface ISkalar<T>
