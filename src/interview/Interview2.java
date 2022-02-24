package interview;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Interview2 {

    public static class Machine{
        public void printBaseInfo(Card input){
            System.out.println(input);
        }
        public void printBox(Box box){
            System.out.println(box.getBaseInfo());
        }

        public void printBoxInternal(Box box){
            System.out.println(box);
        }


    }
    public static class Box{
        private List<Box>boxes = new ArrayList<>();
        private List<Card>cards = new ArrayList<>();

        public void addBox(Box box){
            boxes.add(box);
        }
        public void addCard(Card card){
            cards.add(card);
        }
        public List<Card>getAllCards(){
            List<Card>totalCards = new ArrayList<>(cards);
            for(Box temp:boxes){
                totalCards.addAll(temp.getAllCards());
            }
            return  totalCards;
        }

        public String getBaseInfo(){
            StringBuilder sb = new StringBuilder();
            double totalArea = 0 ;
            double totalAround = 0;
            for(Card temp:getAllCards()){
                totalArea += temp.area;
                totalAround += temp.round;
            }
            sb.append("totalArea:" +totalArea);
            sb.append("totalAround:" +totalAround);
            return sb.toString();
        }

        @Override
        public String toString() {
            StringBuilder result = new StringBuilder();
            //level 每层多两个空格
            int level = 0;
            LinkedList<Box>queue = new LinkedList<>();
            LinkedList<Box>nextQueue = new LinkedList<>();
            queue.add(this);
            while (!queue.isEmpty()){
                Box first = queue.removeFirst();
                for(int i=0;i<level;++i){
                    result.append("  ");
                }
                result.append(first.getBaseInfo()).append(" ");
                for (Card card : first.getAllCards()) {
                    result.append(card);
                }
                nextQueue.addAll(first.boxes);

            }
            return  result.toString();
        }
    }

    public static abstract class Card {
        String view;
        double area;
        double round;

        public Card(String view, double area, double round) {
            this.view = view;
            this.area = area;
            this.round = round;
        }

        @Override
        public String toString() {
            return "Card{" +
                    "view='" + view + '\'' +
                    ", area=" + area +
                    ", round=" + round +
                    '}';
        }
    }

    public static class Circle extends Card {
        public Circle(double r) {
            super("圆形", Math.PI * r * r, 2 * Math.PI * r);
        }
    }


    public static class LongSquare extends Card{
        public LongSquare(double width, double height) {
            super("长方形",  width * height, 2 * (width + height));
        }
    }

    public static class Square extends Card{
        public Square(double r) {
            super("正方形",  r * r, 4 * r);
        }
    }


    public static void main(String[] args) {
        Machine newMachine = new Machine();
//        newMachine.printBaseInfo( new Circle(1) );
//        newMachine.printBaseInfo( new LongSquare(1,2) );
//        newMachine.printBaseInfo( new Square(1) );

        Box myBox = new Box();
        myBox.addCard( new LongSquare(1,2) );
        myBox.addCard( new Square(1) );
        newMachine.printBox(myBox);


        Box myBox2 = new Box();
        myBox2.addCard( new LongSquare(1,2) );
        myBox2.addCard( new Square(1) );
        myBox.addBox(myBox2);
        newMachine.printBox(myBox);
        newMachine.printBox(myBox2);

    }

}
