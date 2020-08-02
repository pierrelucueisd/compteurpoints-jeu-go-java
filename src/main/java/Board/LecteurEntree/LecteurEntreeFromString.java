package Board.LecteurEntree;

import java.util.Scanner;

public class LecteurEntreeFromString implements LecteurEntree {

    private Scanner sc;

    public LecteurEntreeFromString(String input) {
        this.sc = new Scanner(input);
    }

    @Override
    public boolean hasNext() {
        return this.sc.hasNext();
    }

    @Override
    public String next() {
        return this.sc.next();
    }
}
