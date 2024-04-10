package skalary;

import java.lang.reflect.Constructor;

public class Całkowita extends Skalar<Integer> {

    // konstruktor
    public Całkowita(Integer wartość){
        super(wartość);
    } //Całkowita(int)

    // dodanie siebie do innego skalaru
    public Skalar<Integer> suma(Skalar<Integer> składnik) {
        int sumaWartości = this.wartość + składnik.wartość();
        return new Całkowita(sumaWartości);
    } //suma(Skalar<Integer>)

    // pomnożenie przez siebie innego skalaru
    public Skalar<Integer> iloczyn(Skalar<Integer> czynnik) {
        int iloczynWartości = this.wartość * czynnik.wartość();
        return new Całkowita(iloczynWartości);
    } //iloczyn(Skalar<Integer>)

    // wartość bezwzględna
    public Skalar<Integer> bezwzględna() {
        return new Całkowita(Math.abs(this.wartość));
    } //bezwzględna()

    // poproś o zero
    public Skalar<Integer> zero() {
        return new Całkowita(0);
    } //zero()

    @Override
    public int compareTo(Integer innaWartość) {
        if (this.wartość == innaWartość)
            return 0;
        else if (this.wartość > innaWartość)
            return 1;
        else // this.wartość < innaWartość
            return -1;
    } //compareTo

} //class Całkowita
