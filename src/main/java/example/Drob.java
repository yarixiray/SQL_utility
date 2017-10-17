package example;

public class Drob {
    private int num;
    private int denum;

    Drob(int num, int denum) {
        this.num = num;
        this.denum = denum;
    }

    Drob(){}

    public int getNum() {
        return num;
    }

    public int getDenum() {
        return denum;
    }

    public Drob setNum(int num) {
        this.num = num;
        return this;
    }

    public Drob setDenum(int denum) {
        this.denum = denum;
        return this;
    }

    @Override
    public String toString() {
        return num + "/" + denum;
    }
}
