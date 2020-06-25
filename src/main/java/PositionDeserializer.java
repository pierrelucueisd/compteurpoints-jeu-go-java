import java.util.ArrayList;
import java.util.Optional;

public class PositionDeserializer implements Deserializer<Position> {

    private int boardSize;
    private ArrayList<Character> axeHorizontal;
    private ArrayList<Integer> axeVertical;


    PositionDeserializer(int s) {
        this.boardSize = s;
        axeHorizontal = new ArrayList<Character>();
        axeVertical = new ArrayList<Integer>();
        Character c = 'A';
        for(int i=0; i<this.boardSize; i++) {
            this.axeHorizontal.add(c);
            this.axeVertical.add(i+1);
            c++;
        }
    }

    @Override
    public Optional<Position> deserialize(String mot) {
        if(mot.length() != 2 && !Character.isDigit(mot.charAt(1))) return Optional.empty();
        Character carH = mot.charAt(0);
        Integer carV = mot.charAt(1) - '0';
        if(!axeHorizontal.contains(carH) || !axeVertical.contains(carV)) return Optional.empty();
        Integer posX = axeHorizontal.indexOf(carH);
        Integer posY = axeVertical.indexOf(carV);
        return Optional.of(new Position(posX, posY));
    }

    @Override
    public boolean validate(Position position) {
        return false;
    }


}
