package poker;

// import java.util.concurrent.ThreadLocalRandom;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;

public class TexasPoker implements Poker{
    // key: index(card as int), value: numbers of the card.
    private HashMap<Integer, Integer> xCards = new HashMap<>();
    private HashMap<Integer, Integer> yCards = new HashMap<>();
    private ArrayList<String> xCardsList = new ArrayList<>();
    private ArrayList<String> yCardsList = new ArrayList<>();
    // true means X, false means Y.
    //public boolean turn = true;
    public boolean finalState = false;

    @Override
    public void initCard(){

        for (int i = 0; i < letterCards.length; i++) {
            cards.put(letterCards[i], i);
            cardsCount.put(letterCards[i], 4);
        }
    }

    @Override
    public void play() {
        addCard('X', nextCard());
        addCard('Y', nextCard());
    }


    private int nextCard(){
        int index = Rand.randInt(0, 12);
        while (cardsCount.get(letterCards[index]) == 0){
            index = Rand.randInt(0, 12);
        }
        return index;
    }

    private void addCard(char player, int index){
        if (player == 'X'){
            if (xCards.containsKey(index)){
                xCards.put(index, xCards.get(index) + 1);
            }
            else{
                xCards.put(index, 1);
            }
            xCardsList.add(letterCards[index]);
        }
        else{
            if (yCards.containsKey(index)){
                yCards.put(index, yCards.get(index) + 1);
            }
            else{
                yCards.put(index, 1);
            }
            yCardsList.add(letterCards[index]);
        }
    }

    // 0 means X wins, 1 means Y wins.
    public int evaluate(){
        int xMax = 0;
        int yMax = 0;
        int result = -1;
        for (int i = 1; i < 6; i++) {
            if (xCards.containsValue(i)){
                xMax = i;
            }
            if (yCards.containsValue(i)){
                yMax = i;
            }
        }
        if (xMax > yMax){
            result = 0;
        }
        else if(yMax > xMax){
            result = 1;
        }
        else if (xMax == yMax && xMax == 1){
            int xSum = 0;
            int ySum = 0;
            for (int xKey:xCards.keySet()) {
                xSum += xKey;
            }
            for (int yKey:yCards.keySet()) {
                ySum += yKey;
            }
            if (xSum > ySum){
                result = 0;
            }
            else if (xSum < ySum){
                result = 1;
            }
        }
        else{
            ArrayList<Integer> xArray = new ArrayList<>();
            ArrayList<Integer> yArray = new ArrayList<>();
            for (int key:xCards.keySet()) {
                if (xCards.get(key) == xMax){
                    xArray.add(key);
                }
            }

            for (int key:yCards.keySet()) {
                if (yCards.get(key) == xMax){
                    yArray.add(key);
                }
            }
            // x has
            if (xArray.size() > yArray.size()){
                result = 0;
            }
            else if(xArray.size() < yArray.size()){
                result = 1;
            }

            else{
                int xKeyMax = -1;
                int yKeyMax = -1;
                for (int xkey:xArray) {
                    if (xkey > xKeyMax){
                        xKeyMax = xkey;
                    }
                }
                for (int ykey:yArray) {
                    if (ykey > yKeyMax){
                        yKeyMax = ykey;
                    }
                }
                if (xKeyMax > yKeyMax){
                    result = 0;
                }
                else if(xKeyMax < yKeyMax){
                    result = 1;
                }
                else{
                    for (int key1:xArray) {
                        xCards.remove(key1);
                    }
                    for (int key2:yArray) {
                        yCards.remove(key2);
                    }
                    result = this.evaluate();
                }
            }
        }

        return result;
    }

    public void printCurrentStage(){
        int i = 0;
        String cardy;
        for(String ycard:yCardsList) {
            if (!this.finalState && i == 0){
                cardy = "* ";
            }
            else{
                cardy = ycard + " ";
            }
            System.out.print(cardy);
            i ++;
        }

        System.out.println();
        System.out.println("------------------------------");

        for(String xcard:xCardsList) {
            String card = xcard + " ";
            System.out.print(card);
        }
        System.out.println();
    }

    public void isFinalStage(){
        this.finalState = true;
    }

}
