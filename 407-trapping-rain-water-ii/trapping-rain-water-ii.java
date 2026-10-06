class Solution {
    public int trapRainWater(int[][] h) {
        int m = h.length, n = h[0].length, ans = 0;
        if (m < 3 || n < 3) return 0;

        PriorityQueue<int[]> q = new PriorityQueue<>((a,b) -> a[0]-b[0]);
        boolean[][] v = new boolean[m][n];

        for (int i = 0; i < m; i++) {
            q.add(new int[]{h[i][0],i,0});
            q.add(new int[]{h[i][n-1],i,n-1});
            v[i][0] = v[i][n-1] = true;
        }

        for (int j = 1; j < n-1; j++) {
            q.add(new int[]{h[0][j],0,j});
            q.add(new int[]{h[m-1][j],m-1,j});
            v[0][j] = v[m-1][j] = true;
        }

        int[][] d = {{1,0},{-1,0},{0,1},{0,-1}};

        while (!q.isEmpty()) {
            int[] x = q.poll();

            for (int[] z : d) {
                int i = x[1]+z[0], j = x[2]+z[1];

                if (i<0 || i>=m || j<0 || j>=n || v[i][j]) continue;

                v[i][j] = true;
                ans += Math.max(0, x[0]-h[i][j]);
                q.add(new int[]{Math.max(x[0],h[i][j]),i,j});
            }
        }

        return ans;
    }
}