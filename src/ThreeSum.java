import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Given an integer array nums, return all the triplets [nums[i], nums[j], nums[k]] such that i != j, i != k, and j != k, and nums[i] + nums[j] + nums[k] == 0.
 *
 * Notice that the solution set must not contain duplicate triplets.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [-1,0,1,2,-1,-4]
 * Output: [[-1,-1,2],[-1,0,1]]
 * Explanation:
 * nums[0] + nums[1] + nums[2] = (-1) + 0 + 1 = 0.
 * nums[1] + nums[2] + nums[4] = 0 + 1 + (-1) = 0.
 * nums[0] + nums[3] + nums[4] = (-1) + 2 + (-1) = 0.
 * The distinct triplets are [-1,0,1] and [-1,-1,2].
 * Notice that the order of the output and the order of the triplets does not matter.
 * Example 2:
 *
 * Input: nums = [0,1,1]
 * Output: []
 * Explanation: The only possible triplet does not sum up to 0.
 * Example 3:
 *
 * Input: nums = [0,0,0]
 * Output: [[0,0,0]]
 * Explanation: The only possible triplet sums up to 0.
 */
public class ThreeSum {
    public static void main(String[] args) {
        List nums = new ArrayList<>(List.of(-1,0,1,2,-1,-4)); // -4,-1,-1,0,1,2
        List<List<Integer>> answer = getThreeSum(nums);
        System.out.println(answer);
    }

    public static List<List<Integer>> getThreeSum(List<Integer> nums) {
        Collections.sort(nums);
        List<List<Integer>> answers = new ArrayList<>();
        for(int i=0; i<nums.size(); i++) {
            if(i>0 && nums.get(i).equals(nums.get(i-1))) {
                continue;
            }
            for(int j=i+1, k=nums.size()-1; j<k; ) {
                int sum = nums.get(i) + nums.get(j) + nums.get(k);
                if(sum == 0) {
                    answers.add(List.of(nums.get(i), nums.get(j), nums.get(k)));
                    while(j<k && nums.get(j).equals(nums.get(j+1))) {
                        j++;
                    }
                    while(j<k && nums.get(k).equals(nums.get(k-1))) {
                        k--;
                    }
                    j++;
                    k--;
                }
                if(sum < 0) {
                    j++;
                }
                if(sum > 0) {
                    k--;
                }
            }
        }
        return answers;
    }
}
