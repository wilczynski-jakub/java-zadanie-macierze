package skalary;

public class Napis extends Skalar<String> {

    // konstruktor
    public Napis(String wartość){
        super(wartość);
    } //Napis(String)

    // dodanie siebie do innego skalaru
    public Skalar<String> suma(Skalar<String> składnik) {
        String sumaWartości = this.wartość + składnik.wartość();
        return new Napis(sumaWartości);
    } //suma(Skalar<Napis>)

    // pomnożenie przez siebie innego skalaru
    public Skalar<String> iloczyn(Skalar<String> drugiNapis) {
        String wielokrotnePowielenie = "";

        // przechodzenie przez każdy znak swojej wartości
        for (int i=0; i<this.wartość.length(); i++) {
            // (...) poprzedzone za każdym razem znakiem z pierwszego napisu
            wielokrotnePowielenie += this.wartość.charAt(i);

            // wielokrotne powielenie drugiego napisu (...)
            wielokrotnePowielenie += drugiNapis;
        } //for

        return new Napis(wielokrotnePowielenie);
    } //iloczyn(Skalar<Napis>)

    // wartość bezwzględna
    public Skalar<String> bezwzględna() {
        // operacja tożsamościowa
        return new Napis(this.wartość);
    } //bezwzględna()

    // poproś o zero
    public Skalar<String> zero() {
        return new Napis("");
    } //zero()

    @Override
    public int compareTo(String innaWartość) {
        if (this.wartość.length() == innaWartość.length())
            return 0;
        else if (this.wartość.length() > innaWartość.length())
            return 1;
        else // this.wartość.length() < innaWartość.length()
            return -1;
    } //compareTo

} //class Napis
