import java.util.Iterator;

public class CarHashSet implements CarSet ,Iterable<Car>{

    private int size = 0;
    private static final int INITIAL_CAPACITY = 16;
    private static final double LOAD_FACTOR = 0.75;
    private Entry[] array = new Entry[INITIAL_CAPACITY];

    public boolean add(Car car) {
        if(size >= array.length * LOAD_FACTOR) {
            increaseArray();
        }
        boolean added = add(car,array);
        if(added) {
            size++;
        }
        return added;

    }

    public boolean remove(Car car) {
        int position = getElementPosition(car, array.length);
        if (array[position] == null) {
            return false;
        }
        Entry secondLast = array[position];
        Entry last = secondLast.next;
        if (secondLast.value.equals(car)) {
            array[position] = last;
            size--;
            return true;
        }
        while (last != null) {
            if (last.value.equals(car)) {
                secondLast.next = last.next;
                size--;
                return true;
            } else {
                secondLast = last;
                last = last.next;
            }
        }
        return false;
    }

    public int size() {
        return size;
    }
    private void increaseArray() {
       Entry[] newArray = new Entry[array.length * 2];
       for (Entry entry:array) {
           Entry existedElement = entry;
           while (existedElement != null) {
               add(existedElement.value, newArray);
               existedElement = existedElement.next;
           }
       }
        //System.arraycopy(array, 0, newArray, 0, array.length);
        array = newArray;
    }

    private boolean add(Car car ,Entry[] dst) {
        int position = getElementPosition(car, dst.length);
        if (dst[position] == null) {
            Entry entry = new Entry(car, null);
            dst[position] = entry;
            return true;
        } else {
            Entry existedElement = dst[position];
            while (true) {
                if (existedElement.value.equals(car)) {
                    existedElement.value = car;
                    return false;
                } else if (existedElement.next == null) {
                    existedElement.next = new Entry(car, null);
                    return true;
                } else {
                    existedElement = existedElement.next;
                }
            }
        }
    }

    public void clear() {
        array = new Entry[INITIAL_CAPACITY];
        size = 0;
    }

    @Override
    public boolean contains(Car car) {
        int position = getElementPosition(car, array.length);
        Entry existedElement = array[position];
        while (existedElement != null) {
            if(existedElement.value.equals(car)){
                return true;
            }
            existedElement = existedElement.next;
        }
        return false;


    }

    private int getElementPosition(Car car,int arrayLength) {
          return Math.abs(car.hashCode() % arrayLength);
    }

    @Override
    public Iterator<Car> iterator() {
        return new Iterator<>() {
            int position;
            Entry last;
            Entry current;
            int count;

            @Override
            public boolean hasNext() {
                return count < size;
            }

            @Override
            public Car next() {

                while (last == null) {
                    last = array[position++];
                }
                count++;
                current = last;
                last = last.next;
                return current.value;
            }
        };

    }

    private static class Entry {
        private Car value;
        private Entry next;

        public Entry(Car value, Entry next) {
            this.value = value;
            this.next = next;
        }
    }
}
