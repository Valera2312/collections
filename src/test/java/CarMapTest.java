import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CarMapTest {
    CarMap carMap;
    @BeforeEach
    void setUp() {
        carMap = new CarHashMap();
    }
    @Test
    public void whenPut100ElementsThenSizeBecome100() {
        for (int i = 0; i < 100; i++) {
            CarOwner carOwner = new CarOwner(i, "Name" + i, "LastName" + i);
            Car car = new Car("Brand" + i, i);
            carMap.put(carOwner, car);
        }
        assertEquals(100,carMap.size());
    }
    @Test
    public void removeReturnTrueOnlyOnce() {
        for (int i = 0; i < 100; i++) {
            CarOwner carOwner = new CarOwner(i, "Name" + i, "LastName" + i);
            Car car = new Car("Brand" + i, i);
            carMap.put(carOwner, car);
        }
        assertEquals(100, carMap.size());

        CarOwner elementForDeleting = new CarOwner(50, "Name50", "LastName50");
        assertTrue(carMap.remove(elementForDeleting));
        assertEquals(99, carMap.size());
        assertFalse(carMap.remove(elementForDeleting));
        assertNull(carMap.get(elementForDeleting));
    }

    @Test
    public void countOfKeysMustBeEqualsToCountOfValues() {
        for (int i = 0; i < 100; i++) {
            CarOwner carOwner = new CarOwner(i, "Name" + i, "LastName" + i);
            Car car = new Car("Brand" + i, i);
            carMap.put(carOwner, car);
        }
        assertEquals(100, carMap.size());
        assertEquals(100, carMap.keySet().size());
        assertEquals(100, carMap.values().size());
    }

    @Test
    public void methodGetMustReturnRightValue() {
        for (int i = 0; i < 100; i++) {
            CarOwner carOwner = new CarOwner(i, "Name" + i, "LastName" + i);
            Car car = new Car("Brand" + i, i);
            carMap.put(carOwner, car);
        }
        CarOwner key = new CarOwner(50, "Name50", "LastName50");
        Car value = carMap.get(key);
        String expectedCarBrand = "Brand50";
        assertEquals(expectedCarBrand, value.getBrand());
    }
    @Test
    public void deleteLastAndFirstElement() {
        for (int i = 0; i < 10; i++) {
            CarOwner carOwner = new CarOwner(i, "Name" + i, "LastName" + i);
            Car car = new Car("Brand" + i, i);
            carMap.put(carOwner, car);
        }
        CarOwner elementForDeleting = new CarOwner(0, "Name0", "LastName0");
        carMap.remove(elementForDeleting);
        assertEquals(9,carMap.size());
        CarOwner elementForDeleting2 = new CarOwner(9, "Name9", "LastName9");
        carMap.remove(elementForDeleting2);
        assertEquals(8,carMap.size());
    }
    @Test
    public void keySet() {
        for (int i = 0; i < 10; i++) {
            CarOwner carOwner = new CarOwner(i, "Name" + i, "LastName" + i);
            Car car = new Car("Brand" + i, i);
            carMap.put(carOwner, car);
        }
        for (CarOwner carOwner:
             carMap.keySet()) {
            System.out.println(carOwner.getId() + " " + carOwner.getName() + " " + carOwner.getLastname());
        }
    }
    @Test
    public void values() {
        for (int i = 0; i < 10; i++) {
            CarOwner carOwner = new CarOwner(i, "Name" + i, "LastName" + i);
            Car car = new Car("Brand" + 1, 1);
            carMap.put(carOwner, car);
        }
        for (Car car:
                carMap.values()) {
            System.out.println(car.getBrand() + " " + car.getNumber());
        }
    }

}