package macierze;
import skalary.Skalar;

public interface IMacierz<T> {

    IMacierz<T> suma(IMacierz<T> skladnik) throws InnyRozmiar;
    IMacierz<T> iloczyn(Skalar<T> skalar);
    Skalar<T> norma1();
    Skalar<T> element(int wiersz, int kolumna) throws ZłyIndeks;
    int ileWierszy();
    int ileKolumn();

    class InnyRozmiar extends Exception {
        public InnyRozmiar(String macierz1, String macierz2) {
            super("Nie zgadzają się rozmiary macierzy.\nProszę sprawdzić:\n"+macierz1+macierz2);
        } //InnyRozmiar()
    } //class InnyRozmiar

    class ZłyIndeks extends Exception {
        private final String przyczyna;

        public ZłyIndeks(String przyczyna, int x, int y) {
            super("Nie mogę uzyskać elementu o indeksach ("+x+","+y+"). Proszę sprawdzić poniższą macierz:\n"+przyczyna+"\n");
            this.przyczyna = przyczyna;
        } //ZłyIndeks()

        public String przyczyna() {
            return this.przyczyna;
        } //przyczyna()
    } //class ZłyIndeks

} //interface IMacierz<S>
