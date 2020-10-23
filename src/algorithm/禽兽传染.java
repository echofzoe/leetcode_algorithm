package algorithm;

public class 禽兽传染 {

    public static void main(String[] args) {
        禽兽传染 main = new 禽兽传染();

        System.out.println("output: " + main.funcEntrance(10, 2));
    }

    private int funcEntrance(int x, int rounds) {
        this.x = x;
        this.rounds = rounds;
        this.count = 0;

        recur(0);

        return count;
    }

    int x;
    int rounds;
    int count;

    private void recur(int curRounds) {
        if (curRounds != rounds) {
            recur(++curRounds);
        }

        if (count == 0) count += 1;
        else count += count * x;

    }
}
