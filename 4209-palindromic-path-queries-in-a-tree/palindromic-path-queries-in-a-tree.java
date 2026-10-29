import java.util.*;

class Solution {
    static class Fenwick {
        int n;
        int[] tree;

        Fenwick(int n) {
            this.n = n;
            tree = new int[n + 1];
        }

        void update(int index, int value) {
            index++;
            while (index <= n) {
                tree[index] ^= value;
                index += index & -index;
            }
        }

        int query(int index) {
            index++;
            int result = 0;
            while (index > 0) {
                result ^= tree[index];
                index -= index & -index;
            }
            return result;
        }

        int rangeQuery(int left, int right) {
            return query(right) ^ (left == 0 ? 0 : query(left - 1));
        }
    }

    int n;
    int[] parent, depth, size, heavy, head, pos;
    int currentPos;
    Fenwick fenwick;

    public List<Boolean> palindromePath(
            int n,
            int[][] edges,
            String s,
            String[] queries
    ) {
        this.n = n;

        List<Integer>[] graph = new ArrayList[n];

        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            graph[u].add(v);
            graph[v].add(u);
        }

        parent = new int[n];
        depth = new int[n];
        size = new int[n];
        heavy = new int[n];

        Arrays.fill(parent, -1);
        Arrays.fill(heavy, -1);

        int[] order = new int[n];
        int orderSize = 0;
        int[] stack = new int[n];
        int top = 0;

        stack[top++] = 0;

        while (top > 0) {
            int u = stack[--top];
            order[orderSize++] = u;

            for (int v : graph[u]) {
                if (v == parent[u]) {
                    continue;
                }

                parent[v] = u;
                depth[v] = depth[u] + 1;
                stack[top++] = v;
            }
        }

        for (int i = n - 1; i >= 0; i--) {
            int u = order[i];
            size[u] = 1;

            int maxSize = 0;

            for (int v : graph[u]) {
                if (parent[v] != u) {
                    continue;
                }

                size[u] += size[v];

                if (size[v] > maxSize) {
                    maxSize = size[v];
                    heavy[u] = v;
                }
            }
        }

        head = new int[n];
        pos = new int[n];
        currentPos = 0;

        ArrayDeque<int[]> stackChains = new ArrayDeque<>();
        stackChains.push(new int[]{0, 0});

        while (!stackChains.isEmpty()) {
            int[] current = stackChains.pop();

            int u = current[0];
            int chainHead = current[1];

            while (u != -1) {
                head[u] = chainHead;
                pos[u] = currentPos++;

                for (int v : graph[u]) {
                    if (parent[v] == u && v != heavy[u]) {
                        stackChains.push(new int[]{v, v});
                    }
                }

                u = heavy[u];
            }
        }

        fenwick = new Fenwick(n);

        int[] values = new int[n];

        for (int i = 0; i < n; i++) {
            values[i] = 1 << (s.charAt(i) - 'a');
            fenwick.update(pos[i], values[i]);
        }

        List<Boolean> answer = new ArrayList<>();

        for (String query : queries) {
            String[] parts = query.split(" ");

            if (parts[0].equals("update")) {
                int u = Integer.parseInt(parts[1]);
                char c = parts[2].charAt(0);

                int newMask = 1 << (c - 'a');
                int oldMask = values[u];

                if (oldMask != newMask) {
                    fenwick.update(pos[u], oldMask ^ newMask);
                    values[u] = newMask;
                }
            } else {
                int u = Integer.parseInt(parts[1]);
                int v = Integer.parseInt(parts[2]);

                int mask = queryPath(u, v);

                answer.add(
                    mask == 0 ||
                    (mask & (mask - 1)) == 0
                );
            }
        }

        return answer;
    }

    private int queryPath(int u, int v) {
        int result = 0;

        while (head[u] != head[v]) {
            if (depth[head[u]] < depth[head[v]]) {
                int temp = u;
                u = v;
                v = temp;
            }

            result ^= fenwick.rangeQuery(
                pos[head[u]],
                pos[u]
            );

            u = parent[head[u]];
        }

        int left = Math.min(pos[u], pos[v]);
        int right = Math.max(pos[u], pos[v]);

        result ^= fenwick.rangeQuery(left, right);

        return result;
    }
}