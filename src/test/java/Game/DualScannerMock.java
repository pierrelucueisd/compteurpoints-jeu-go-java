package Game;

import java.util.Scanner;

public class DualScannerMock implements IDualScanner {

    private Scanner sc;

    public DualScannerMock(String input) {
        this.sc = new Scanner(input);
    }

    @Override
    public String next() {
        if (sc.hasNext())
            return sc.next();
        sc = new Scanner("PASS PASS");
        return sc.next();
    }
}
