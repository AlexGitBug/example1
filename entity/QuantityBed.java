package entity;

import java.util.Objects;

public class QuantityBed {

    private int id;

    private String capacity;

    public QuantityBed() {
    }

    public QuantityBed(int id, String capacity) {
        this.id = id;
        this.capacity = capacity;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCapacity() {
        return capacity;
    }

    public void setCapacity(String capacity) {
        this.capacity = capacity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        QuantityBed that = (QuantityBed) o;
        return id == that.id && capacity == that.capacity;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, capacity);
    }

    @Override
    public String toString() {
        return "QuantityBed{" +
                "id=" + id +
                ", capacity=" + capacity +
                '}';
    }
}
