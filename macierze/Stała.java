package macierze;
import skalary.*;

public class Stała<T> extends Macierz<T> {

    private Skalar<T> zawartość;

    public Stała(Skalar<T> zawartość, int ileWierszy, int ileKolumn) {
        this.zawartość = zawartość;
        assert (ileWierszy>0 && ileKolumn>0): "Złe rozmiary macierzy";
        super.ileWierszy = ileWierszy;
        super.ileKolumn = ileKolumn;
    } //Stała(Skalar<T>)

    @Override
    public IMacierz<T> suma(IMacierz<T> składnik) throws InnyRozmiar {

        // jeżeli macierze są tych samych rozmiarów
        if (składnik.ileWierszy() == this.ileWierszy && składnik.ileKolumn() == this.ileKolumn) {
            
            // jeżeli składnik to Stała
            if (składnik.getClass() == Stała.class) {
                try { // raczej nie ma szans na wyjątek ZłyIndeks, ale środowisko i tak każe go przechwycić
                    Skalar<T> zsumowanaZawartość = this.zawartość.suma(składnik.element(0, 0));
                    return new Stała(zsumowanaZawartość, ileWierszy, ileKolumn);
                } catch(ZłyIndeks e) {
                    System.err.println(e.getMessage());
                } //try//catch
            } //if Składnik.getClass() == class Stała

            // jeżeli składnik to pełna
            else if (składnik.getClass() == Pełna.class) {
                Skalar<T>[][] zsumowanaZawartość = new Skalar[ileWierszy][ileKolumn];
                for (int w = 0; w < ileWierszy; w++)
                    for (int k = 0; k < ileKolumn; k++)
                        try { // raczej nie ma szans na wyjątek ZłyIndeks, ale środowisko i tak każe go przechwycić
                            zsumowanaZawartość[w][k] = this.zawartość.suma(składnik.element(w, k));
                        } catch(ZłyIndeks e) {
                            System.err.println(e.getMessage());
                        } //try//catch
                return new Pełna(zsumowanaZawartość);
            } //if "class Pełna"

        } // jeżeli podana macierz jest innego rozmiaru
        throw new InnyRozmiar(this.toString(),składnik.toString());
    } //suma(Macierz<T>)

    @Override
    public IMacierz<T> iloczyn(Skalar<T> czynnik) {
        return new Stała(zawartość.iloczyn(czynnik),ileWierszy,ileKolumn);
    } //iloczyn()

    @Override
    public Skalar<T> norma1() {
        Skalar<T> sumaKolumny = (Skalar<T>)this.zawartość.zero();
        for (int w=0; w<ileWierszy(); w++)
            sumaKolumny = sumaKolumny.suma(this.zawartość.bezwzględna());
        return sumaKolumny;
    } //norma()

    @Override
    public Skalar<T> element(int wiersz, int kolumna) throws ZłyIndeks {
        if (wiersz>=0 && kolumna>=0 && wiersz<ileWierszy && kolumna<ileKolumn)
            return zawartość;
        // jeżeli podane liczby nie mieszczą się w rozmiarze macierzy
        throw new ZłyIndeks(this.toString(),wiersz,kolumna);
    } //element(int,int)

    @Override
    public String toString() {
        String wynik = "\n\t[";
        for (int w = 0; w < ileWierszy(); w++) {
            for (int k = 0; k < ileKolumn(); k++)
                wynik += "\t" + this.zawartość;
            wynik += (w<ileWierszy()-1 ? "\n\t" : "\t]\n");
        } //for(w)
        return wynik;
    } //toString()

} //class Stała
