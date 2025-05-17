import java.util.*;

/**
 * Task Scheduling
 * Given an array task_memory of n positive integers representing the amount of memory required to process each task, an array task_type of n positive integers representing the type of each task, and an integer max_memory, find the minimum amount of time required for the server to process all the tasks.
 *
 * Each task takes 1 unit of time to process. The server can process at most two tasks in parallel only if they are of the same type and together require no more than max_memory units of memory.
 *
 * Example
 * Suppose
 * n = 4,
 * task_memory = [7, 2, 3, 9],
 * task_type = [1, 2, 1, 3],
 * max_memory = 10.
 *
 * One efficient schedule is shown:
 *
 * Task Pair	Task 1	Task 2	Task Type	    Memory Requirement	Within Max Memory	Can Process in Parallel
 * 1              0	       2	   Same	          7 + 3 = 10	            Yes	                    Yes
 * 2	          1	       3	   Different      9 + 2 = 11	            No	                    No
 *
 * Tasks 0 and 2 are processed concurrently, but the other two must be processed separately due to their memory requirements and because they are not the same type. The minimum amount of time required to process all the tasks is 3 units.
 *
 * Function Description
 * Complete the function getMinTime in the editor below.
 *
 * java
 * Copy
 * Edit
 * int getMinTime(int[] task_memory, int[] task_type, int max_memory)
 * Parameters:
 * int task_memory[n]: the memory required by the tasks
 *
 * int task_type[n]: the type of the tasks
 *
 * int max_memory: the maximum total memory that can be allocated to the tasks
 *
 * Returns:
 * int: the minimum time required to process all tasks
 *
 * Constraints
 * 1≤n≤2×10^5
 * 1 \leq \text{max_memory} \leq 10^9
 * 1 \leq \text{task_memory}[i] \leq \text{max_memory}
 */

public class TaskScheduler {

    public static int getMinTime(int[] task_memory, int[] task_type, int max_memory) {
        int n = task_memory.length;

        Map<Integer, List<Integer>> typeToMemoryList = new HashMap<>();
        for (int i = 0; i < n; i++) {
            typeToMemoryList
                    .computeIfAbsent(task_type[i], k -> new ArrayList<>())
                    .add(task_memory[i]);
        }

        int time = 0;

        for (List<Integer> memoryList : typeToMemoryList.values()) {
            Collections.sort(memoryList);
            int left = 0;
            int right = memoryList.size() - 1;

            while (left <= right) {
                if (left == right) {
                    time++;
                    break;
                }
                if (max_memory - (memoryList.get(left) + memoryList.get(right)) >= 0) {
                    left++;
                    right--;
                } else {
                    right--;
                }
                time++;
            }
        }

        return time;
    }

    public static void main(String[] args) {
        int[] task_memory = {1, 2, 3, 4, 2};
        int[] task_type = {1, 2, 1, 2, 3};
        int max_memory = 4;

        int result = getMinTime(task_memory, task_type, max_memory);
        System.out.println("Minimum time required: " + result); // Expected: 4
    }
}

