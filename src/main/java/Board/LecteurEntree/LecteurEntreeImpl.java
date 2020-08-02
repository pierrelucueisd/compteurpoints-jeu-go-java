package Board.LecteurEntree;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class LecteurEntreeImpl implements LecteurEntree{
    private Scanner sc;
    private boolean isFileMode = true;

    public LecteurEntreeImpl(String nomFichier) throws FileNotFoundException {
        this.sc = new Scanner(
                new File(nomFichier)
        );
    }


    public boolean hasNext() {
        if(isFileMode && !this.sc.hasNext()) {
            isFileMode = false;
            this.sc = new Scanner(System.in);
        }
        return this.sc.hasNext();
    }

    public String next() {
        return this.sc.next();
    }
}
