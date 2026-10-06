class Solution {
    public int[] topKFrequent(int[] a, int k) {
        Map<Integer,Integer> m=new HashMap<>();
        for(int x:a)m.put(x,m.getOrDefault(x,0)+1);
        List<Integer>[] b=new List[a.length+1];
        for(int x:m.keySet()){
            int f=m.get(x);
            if(b[f]==null)b[f]=new ArrayList<>();
            b[f].add(x);
        }
        int[] r=new int[k]; int j=0;
        for(int i=a.length;i>0&&j<k;i--)
            if(b[i]!=null)for(int x:b[i])if(j<k)r[j++]=x;
        return r;
    }
}