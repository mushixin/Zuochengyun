package learn;

public class PSon {

    public static class Father{
        int status = 1;
        public void print(){
            System.out.println("father");
            System.out.println(status);
        }
        public int getStatus(){
            return status;
        }
    }

    public static class Son extends Father{
        int status = 2;
        public Son(){
        }
        public Son(int status){
            this.status = status;
        }
        public void print(){
            System.out.println("son");
            System.out.println(status);
        }
    }

    public static void main(String[] args) {
        Father ff = new Son();
        /**
         * 何：son 2
         * 万：son 2
         */
        ff.print();
        /**
         * 何：1
         * 万：1
         */
        System.out.println(ff.getStatus());

        Son son = new Son();
        /**
         * 何：
         * 万：son 2
         */
        son.print();
        System.out.println(son.getStatus());
    }
}
