package LCQuestions.Solutions._2400_2499._2432_TheEmployeeThatWorkedOnTheLongestTask;

public class _2432_TheEmployeeThatWorkedOnTheLongestTask {
    public int hardestWorker(int n, int[][] a) {
        int len = a[0][1], id = a[0][0];
        for (int i = 1; i < a.length; i++){
            int curr = a[i][1] - a[i - 1][1];
            if (curr > len || curr == len && id > a[i][0]){
                len = curr;
                id = a[i][0];
            }
        }
        return id;
    }
}
