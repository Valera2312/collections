import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CarHashSetTest {

    CarSet carSet = new CarHashSet();

    @BeforeEach
    void setUp() {
        for (int i = 0; i < 50; i++) {
            carSet.add(new Car("Brand" + i,i));
        }
    }

    @Test
    void whenTryToAdd3SimilarObj() {
        assertEquals(100,carSet.size());
        Car car = new Car("BMW",6);
        assertTrue(carSet.add(car));
        assertFalse(carSet.add(car));
        assertFalse(carSet.add(car));
        assertEquals(101,carSet.size());
    }
    @Test
    void removeElement() {

        assertTrue(carSet.remove(new Car("Brand30",30)));
        assertEquals(99,carSet.size());

        assertFalse(carSet.remove(new Car("Brand30",30)));
        assertEquals(99,carSet.size());
    }
    @Test
    void clear() {
        carSet.clear();
        assertEquals(0,carSet.size());
    }
    @Test
    void checkExistingElement() {
        assertTrue(carSet.contains(new Car("Brand30",30)));
        assertFalse(carSet.contains(new Car("Brand101",101)));
    }
    @Test
    public void testForeach() {
        int count = 0;
        for (Car car:
             carSet) {
            count++;
            System.out.println(car);
        }
        assertEquals(50,count);
    }

}