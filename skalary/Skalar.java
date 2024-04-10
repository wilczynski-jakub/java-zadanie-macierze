package skalary;

public abstract class Skalar<T> implements ISkalar<T>, Comparable<T> {

    // atrybut wartości skalaru
    protected T wartość;

    // konstruktor
    public Skalar(T wartość) {
        this.wartość = wartość;
    } //Skalar(T)

    // zwróć wartość
    public T wartość() {
        return this.wartość;
    } //wartość()
    
    @Override
    public String toString() {
        return this.wartość.toString();
    } //toString()

} //class Skalar<T>
