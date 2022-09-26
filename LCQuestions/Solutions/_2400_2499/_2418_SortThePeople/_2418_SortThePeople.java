package LCQuestions.Solutions._2400_2499._2418_SortThePeople;

import java.util.Arrays;

public class _2418_SortThePeople {
    public String[] sortPeople(String[] names, int[] heights) {
        int n = names.length;
        People[] people = new People[n];
        for (int i = 0; i < n; i++) {
            people[i] = new People(names[i], heights[i]);
        }
        
        Arrays.sort(people, (a, b) -> b.height - a.height);
        return Arrays.stream(people).map(x -> x.name).toArray(String[]::new);
    }
    
    class People {
        String name;
        int height;
        
        public People(String name, int height) {
            this.name = name;
            this.height = height;
        }
    }

    //Time: O(NlgN); Space: O(N)
    public String[] sortPeople2(String[] names, int[] heights) {

        //Space: O(N)
        int[][] people = new int[names.length][2];
        for (int i = 0; i < names.length; i++)
            people[i]  = new int[] {heights[i], i};

        //Time: O(NlgN); Space: O(lgN)
        Arrays.sort(people, (a, b) -> b[0] - a[0]);

        String[] res = new String[names.length];
        //Time: O(N)
        for (int i = 0; i < names.length; i++)
            res[i] = names[people[i][1]];

        return res;
    }
}
