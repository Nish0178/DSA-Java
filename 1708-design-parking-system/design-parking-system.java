class ParkingSystem {
    int[] parking = new int[3];

    public ParkingSystem(int big, int medium, int small) {
        parking[0] = big;
        parking[1] = medium;
        parking[2] = small;
    }

    public boolean addCar(int carType) {
        if (parking[carType - 1] == 0)
            return false;

        parking[carType - 1]--;
        return true;
    }
}