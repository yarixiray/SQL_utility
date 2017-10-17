package example;

public class Runner {
    public static void main(String[] args) {

        Drob drob = new Drob();
        drob.setNum(4).setDenum(8);
//        Drob drob2 = new Drob(3, 4);

        System.out.println(sum(new Drob(1, 2), new Drob(3, 4)).toString());

    }

    public static Drob sum(Drob drob1, Drob drob2) {
        return new Drob(drob1.getNum() * drob2.getDenum() + drob1.getDenum() * drob2.getNum(), drob1.getDenum() * drob2.getDenum());
    }

}
