package LCQuestions;

public class _134_GasStation {

    /**
     There are N gas stations along a circular route, where the amount of gas at station i is gas[i].
     You have a car with an unlimited gas tank and it costs cost[i] of gas to travel from station i
     to its next station (i+1). You begin the journey with an empty tank at one of the gas stations.
     Return the starting gas station's index if you can travel around the circuit once in the clockwise direction,
     otherwise return -1.

     Note:
     If there exists a solution, it is guaranteed to be unique.
     Both input arrays are non-empty and have the same length.
     Each element in the input arrays is a non-negative integer.

     如果一个数组的总和非负，那么一定可以找到一个起始位置，从他开始绕数组一圈，累加和一直都是非负的

     Example 1:
     Input:
     gas  = [1,2,3,4,5]
     cost = [3,4,5,1,2]
     https://www.cnblogs.com/boring09/p/4248482.html

     Output: 3
     * @param gas
     * @param cost
     * @return
     */
    public int canCompleteCircuit(int[] gas, int[] cost) {
        if (gas.length == 0 || cost.length == 0 || gas.length != cost.length) return -1;
        int sum = 0; int total = 0; int k = 0;
        for(int i = 0; i < gas.length; i++) {
            sum += gas[i] - cost[i];
            if (sum < 0) {
                k = i + 1;
                sum = 0;
            }
            total += gas[i] - cost[i];
        }

        if(total < 0) {
            return -1;
        } else {
            return k;
        }
    }
}
