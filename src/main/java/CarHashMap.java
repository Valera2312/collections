import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CarHashMap implements CarMap{
    private int size = 0;
    private static final int INITIAL_CAPACITY = 16;
    private static final double LOAD_FACTOR = 0.75;
    private Entry[] array = new Entry[INITIAL_CAPACITY];
    @Override
    public void put(CarOwner key, Car value) {
        if(size >= array.length * LOAD_FACTOR) {
            increaseArray();
        }
        boolean put = put(key,value,array);
        if(put){
            size++;
        }
    }


    public boolean put(CarOwner key, Car value, Entry[] dst) {
        int position = getElementPosition(key, dst.length);
        if (dst[position] == null) {
            Entry entry = new Entry(key,value, null);
            dst[position] = entry;
            return true;
        } else {
            Entry existedElement = dst[position];
            while (true) {
                if (existedElement.key.equals(key)) {
                    return false;
                } else if (existedElement.next == null) {
                    existedElement.next = new Entry(key,value, null);
                    return true;
                } else {
                    existedElement = existedElement.next;
                }
            }
        }
    }
    private void increaseArray() {
        Entry[] newArray = new Entry[array.length * 2];
        for (Entry entry:array) {
            Entry existedElement = entry;
            while (existedElement != null) {
                put(existedElement.key,existedElement.value , newArray);
                existedElement = existedElement.next;
            }
        }
        array = newArray;
    }

    @Override
    public Car get(CarOwner key) {
        int position = getElementPosition(key, array.length);
        Entry existedElement = array[position];
        while (existedElement != null) {
            if(existedElement.key.equals(key)){
                return existedElement.value;
            }
            existedElement = existedElement.next;
        }
        return null;
    }



    @Override
    public Set<CarOwner> keySet() {
        Set<CarOwner> keySet = new HashSet<>();
        for (Entry entry:array) {
            Entry existedElement = entry;
            while (existedElement != null) {
                keySet.add(existedElement.key);
                existedElement = existedElement.next;
            }
        }
        return keySet;
    }

    @Override
    public List<Car> values() {
        List<Car> values = new ArrayList<>();
        for (Entry entry:array) {
            Entry existedElement = entry;
            while (existedElement != null) {
                values.add(existedElement.value);
                existedElement = existedElement.next;
            }
        }
        return values;
    }

    @Override
    public boolean remove(CarOwner key) {
        int position = getElementPosition(key, array.length);
        if (array[position] == null) {
            return false;
        }
        Entry existedElement = array[position];
        Entry nextElement = existedElement.next;
        if (existedElement.key.equals(key)) {
            array[position] = existedElement.next;
            size--;
            return true;
        }
        while (nextElement != null) {
            if (existedElement.key.equals(key)) {
                existedElement = existedElement.next;
                size--;
                return true;
            } else {
                existedElement = nextElement;
                nextElement = nextElement.next;
            }
        }

        return false;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void clear() {
        array = new Entry[INITIAL_CAPACITY];
        size = 0;
    }
    private int getElementPosition(CarOwner carOwner, int arrayLength) {
        return Math.abs(carOwner.hashCode() % arrayLength);
    }
    static class Entry {
        CarOwner key;
        Car value;
        Entry next;

        public Entry(CarOwner key, Car value, Entry next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }
    }
}
