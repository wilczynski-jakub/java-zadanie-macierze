package macierze;
import skalary.*;

public class Pełna<T> extends Macierz<T> {

    private Skalar<T>[][] zawartość;

    public Pełna(Skalar<T>[][] zawartość) {
        this.zawartość = zawartość;
        assert (zawartość.length>0 && zawartość[0].length>0): "Złe rozmiary macierzy";
        super.ileWierszy = zawartość.length;
        super.ileKolumn = zawartość[0].length;
    } //Stała(Skalar<T>)

    @Override
    public IMacierz<T> suma(IMacierz<T> składnik) throws InnyRozmiar {
        if (składnik.ileWierszy() == this.ileWierszy && składnik.ileKolumn() == this.ileKolumn) {

            Skalar<T>[][] zsumowanaZawartość = new Skalar[ileWierszy()][ileKolumn()];
            for (int w = 0; w < ileWierszy(); w++)
                for (int k = 0; k < ileKolumn(); k++)
                    try { // raczej nie ma szans na wyjątek ZłyIndeks, ale środowisko i tak każe go przechwycić
                        zsumowanaZawartość[w][k] = this.element(w,k).suma(składnik.element(w, k));
                    } catch(ZłyIndeks e) {
                        System.err.println(e.getMessage());
                    } //try//catch

            return new Pełna<>(zsumowanaZawartość);

        } // jeżeli podana macierz jest innego rozmiaru
        throw new InnyRozmiar(this.toString(),składnik.toString());
    } //suma(Macierz<T>)

    @Override
    public IMacierz<T> iloczyn(Skalar<T> czynnik) {

            Skalar<T>[][] pomnożonaZawartość = new Skalar[ileWierszy()][ileKolumn()];
            for (int w = 0; w < ileWierszy(); w++)
                for (int k = 0; k < ileKolumn(); k++)
                    try { // raczej nie ma szans na wyjątek ZłyIndeks, ale środowisko i tak każe go przechwycić
                        pomnożonaZawartość[w][k] = this.element(w,k).iloczyn(czynnik);
                    } catch(ZłyIndeks e) {
                        System.err.println(e.getMessage());
                    } //try//catch

            return new Pełna<>(pomnożonaZawartość);
    } //suma(Macierz<T>)

    @Override
    public Skalar<T> norma1() {
        Skalar<T> maxSuma = this.zawartość[0][0].zero();

        for (int k=0; k<ileKolumn(); k++) {
            Skalar<T> sumaKolumny = this.zawartość[0][0].zero();

            for (int w=0; w<ileWierszy(); w++) {
                try { // raczej nie ma szans na wyjątek ZłyIndeks, ale środowisko i tak każe go przechwycić
                    if (sumaKolumny == null) // jeżeli to pierwszy element w kolumnie
                        sumaKolumny = this.element(w, k).bezwzględna();
                    else // dodajemy do poprzednich elementów
                        sumaKolumny = sumaKolumny.suma(this.element(w, k).bezwzględna());
                } catch (ZłyIndeks e) {
                    System.err.println(e.getMessage());
                } //try//catch
            } //for(k)

            // jeżeli suma modułów tej kolumny okazała się większa niż uzyskana dotychczas
            if (sumaKolumny.compareTo(maxSuma.wartość()) > 0)
                maxSuma = sumaKolumny;

        } //for(w)
        return maxSuma;
    } //norma1()

    @Override
    public Skalar<T> element(int wiersz, int kolumna) throws ZłyIndeks {
        if (wiersz>=0 && kolumna>=0 && wiersz<ileWierszy() && kolumna<ileKolumn())
            return zawartość[wiersz][kolumna];
        // jeżeli podane liczby nie mieszczą się w rozmiarze macierzy
        throw new ZłyIndeks(this.toString(),wiersz,kolumna);
    } //element(int,int)

    @Override
    public String toString() {
        String wynik = "\n\t[";
        for (int w = 0; w < ileWierszy(); w++) {
            for (int k = 0; k < ileKolumn(); k++)
                wynik += "\t" + this.zawartość[w][k];
            wynik += (w<ileWierszy()-1 ? "\n\t" : "\t]\n");
        } //for(w)
        return wynik;
    } //toString()

} //class Pełna
