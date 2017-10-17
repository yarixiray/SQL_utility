package example;

public class Collections {
    public static void main(String[] args) {

        int [][] massive = {
                {32,12,321,54,87,65},
                {24,44,6},
                {5,7,9}
        };
for (int i=0; i<massive.length; i++) {
    for(int j=0; j<massive[i].length;j++) {
        System.out.print(massive[i][j] + "\t");
    }
    System.out.println();
}
    }
}