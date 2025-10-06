package Demo.RestAssured;

import java.util.Arrays;

public class l1 {
    public static void main(String[] args) {
        int[] nums = {0, 0, 1, 1, 1, 2, 2, 3, 3, 4};
        int x = 0;
        int n = nums.length;
        System.out.println(n);
        for (int i = 0; i < n; i++) {
            System.out.println(i);
            if (nums[i]>nums[x]) {
                x = x + 1;
                nums[x]=nums[i];
            }
        }
        System.out.println(Arrays.toString(nums));
        System.out.println(x);
    }
}
