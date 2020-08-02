package Game;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class DualScanner implements IDualScanner {
    private Scanner sc;

    public DualScanner()  {
        this.sc = new Scanner(System.in);
    }

    public DualScanner(String fileName)  {
        try {
            this.sc = new Scanner(new File(fileName));
        } catch (FileNotFoundException e){
            System.out.print("File not found, console mode activated.");
            this.sc = new Scanner(System.in);
        }
    }

    public String next() {
        if (!sc.hasNext())
            sc = new Scanner(System.in);
        return sc.next();
    }
}
