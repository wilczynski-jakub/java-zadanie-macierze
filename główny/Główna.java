package główny;
import macierze.*;
import skalary.*;

public class Główna {

    public <T extends Comparable<T>> void test_dowolna(IMacierz<T> m1, IMacierz<T> m2, Skalar<T> skalar){
        System.out.println("m1 = " + m1);
        System.out.println("m2 = " + m2);

        try {
            System.out.println("m1+m2 = " + m1.suma(m2));
            System.out.println("m2+m1 = " + m2.suma(m1));
        } catch (IMacierz.InnyRozmiar e) {
            System.out.println("Błąd: " + e);
        }

        System.out.println("m1x" + skalar + " = " + m1.iloczyn(skalar));
        System.out.println("m2x" + skalar + " = " + m2.iloczyn(skalar));

        System.out.println("norma(m1)=" + m1.norma1());
        System.out.println("norma(m2)=" + m2.norma1());

    } //test_dowolna(IMacierz<T>, IMacierz<T>, T)

    public void test(){
        Integer n = 2;
        Integer m = 3;

        assert n>0 && m > 0: "Złe rozmiary macierzy";

        Całkowita[][] daneC1 = new Całkowita[n][m];
        Całkowita[][] daneC2 = new Całkowita[n][m];

        Napis[][] daneN1 = new Napis[n][m];
        Napis[][] daneN2 = new Napis[n][m];

        for(int i=0; i<daneC1.length; i++)
            for(int j=0; j<daneC1[0].length; j++) {
                daneC1[i][j] = new Całkowita(i * daneC1[0].length + j);
                daneC2[i][j] = new Całkowita(2*(i * daneC2[0].length + j));
                daneN1[i][j] = new Napis("" + (char)('a'+((i * daneN1[0].length + j)%('z'-'a'+1))));
                daneN2[i][j] = new Napis("" + (char)('a'+((2*(i * daneN2[0].length + j))%('z'-'a'+1))));
            }

        IMacierz<Integer> mpc = new Pełna<>(daneC1);
        IMacierz<String> mpn = new Pełna<>(daneN1);
        IMacierz<Integer> msc = new Stała<>(new Całkowita(3), n, m);
        IMacierz<String> msn = new Stała<>(new Napis("b"), n, m);

        test_dowolna(mpc, msc, new Całkowita(12));
        test_dowolna(mpn, msn, new Napis("_"));
    } //test()

    public static void main(String[] args){
        Główna g = new Główna();
        g.test();
    } //main(String[])

} //class Główna
