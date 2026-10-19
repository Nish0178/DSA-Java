class UndergroundSystem {

    Map<Integer, Pair> check = new HashMap<>();
    Map<String, double[]> trips = new HashMap<>();

    public UndergroundSystem() {}

    public void checkIn(int id, String stationName, int t) {
        check.put(id, new Pair(stationName, t));
    }

    public void checkOut(int id, String stationName, int t) {
        Pair p = check.get(id);
        String key = p.station + "#" + stationName;

        double[] x = trips.getOrDefault(key, new double[2]);
        x[0] += t - p.time;
        x[1]++;
        trips.put(key, x);

        check.remove(id);
    }

    public double getAverageTime(String startStation, String endStation) {
        double[] x = trips.get(startStation + "#" + endStation);
        return x[0] / x[1];
    }

    static class Pair {
        String station;
        int time;

        Pair(String station, int time) {
            this.station = station;
            this.time = time;
        }
    }
}