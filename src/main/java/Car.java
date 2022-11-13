public class Car {
    String brand;
    int number;

    public int getNumber() {
        return number;
    }

    public Car(String brand, int number) {
        this.brand = brand;
        this.number = number;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public Car() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Car car = (Car) o;

        if (number != car.number) return false;
        return brand.equals(car.brand);
    }

    @Override
    public int hashCode() {
            int result = brand.hashCode();
            result = 31 * result + number;
            return result;
          //return super.hashCode();
    }



    @Override
    public String toString() {
        return "Car{" +
                "brand='" + brand + '\'' +
                ", number=" + number +
                '}';
    }
}


