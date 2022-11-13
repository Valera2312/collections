import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CarMapTest {
    CarMap carMap;
    @BeforeEach
    void setUp() {
        for (int i = 0; i < 100; i++) {
            carMap.put(new CarOwner(i,"name" + i,"lastname" + i),
                       new Car("Brand" + i,i));
        }
    }
    @Test
    void whenPut() {
        assertEquals(100,carMap.size());
        carMap.put(new CarOwner(1,"Valery","Proskuryakov"),
                new Car("BMW",1));
        assertEquals(101,carMap.size());
        carMap.put(new CarOwner(1,"Valery","Proskuryakov"),
                new Car("BMW",1));
        carMap.put(new CarOwner(1,"Valery","Proskuryakov"),
                new Car("BMW",1));
        carMap.put(new CarOwner(1,"Valery","Proskuryakov"),
                new Car("BMW",1));
        assertEquals(101,carMap.size());
    }

}