package macierze;
import skalary.Skalar;

public abstract class Macierz<T> implements IMacierz<T> {

    protected int ileWierszy;
    protected int ileKolumn;

    @Override
    public int ileWierszy() {
        return ileWierszy;
    } //ileWierszy()

    @Override
    public int ileKolumn() {
        return ileKolumn;
    } //ileKolumn()

} //class Macierz<T>
