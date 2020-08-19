package JavaSE_basic.day02;

public class ArraysTest {
    public static void main(String[] args) {

        // 10 行杨辉三角
        int[][] yanghui = new int[10][];
        yanghui[0] = new int[]{1};
        yanghui[1] = new int[]{1, 1};


        for (int i = 2; i < yanghui.length; i++) {
            yanghui[i] = new int[i + 1];
            yanghui[i][0] = 1;
            yanghui[i][i] = 1;
            for (int j = 1; j < yanghui[i].length - 1; j++) {
                yanghui[i][j] = yanghui[i - 1][j - 1] + yanghui[i - 1][j];
            }
        }

        for (int i = 0; i < yanghui.length; i++) {
            for (int j = 0; j < yanghui[i].length; j++) {
                System.out.print(yanghui[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println("*********************************");

        // 生成6元素数组，各元素值需介于[1, 30]且各不相同
        int[] nums = new int[6];
        for (int i = 0; i < nums.length; i++) {
            nums[i] = (int) (Math.random() * 30) + 1;
            for (int j = 0; j < i; j++) {
                if (nums[i] == nums[j]) {
                    i--;
                    break;
                }
            }
        }
        for (int i = 0; i < nums.length; i++) {
            System.out.print(nums[i] + " ");
        }
    }
}
