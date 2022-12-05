package entity;

import java.math.BigDecimal;
import java.util.Objects;

public class Room {

    private int id;
    private int numberRoom;
    private int quantityBedId;
    private int categoryRoomId;
    private int floor;
    private BigDecimal dayPrice;

    public Room() {
    }

    public Room(int id, int number, int quantityBedId, int categoryRoomId, int floor, BigDecimal dayPrice) {
        this.id = id;
        this.numberRoom = number;
        this.quantityBedId = quantityBedId;
        this.categoryRoomId = categoryRoomId;
        this.floor = floor;
        this.dayPrice = dayPrice;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNumberRoom() {
        return numberRoom;
    }

    public void setNumberRoom(int numberRoom) {
        this.numberRoom = numberRoom;
    }

    public int getQuantityBedId() {
        return quantityBedId;
    }

    public void setQuantityBedId(int quantityBedId) {
        this.quantityBedId = quantityBedId;
    }

    public int getCategoryRoomId() {
        return categoryRoomId;
    }

    public void setCategoryRoomId(int categoryRoomId) {
        this.categoryRoomId = categoryRoomId;
    }

    public int getFloor() {
        return floor;
    }

    public void setFloor(int floor) {
        this.floor = floor;
    }

    public BigDecimal getDayPrice() {
        return dayPrice;
    }

    public void setDayPrice(BigDecimal dayPrice) {
        this.dayPrice = dayPrice;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Room room = (Room) o;
        return id == room.id && numberRoom == room.numberRoom && quantityBedId == room.quantityBedId && categoryRoomId == room.categoryRoomId && floor == room.floor && Objects.equals(dayPrice, room.dayPrice);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, numberRoom, quantityBedId, categoryRoomId, floor, dayPrice);
    }

    @Override
    public String toString() {
        return "Room{" +
                "id=" + id +
                ", numberRoom=" + numberRoom +
                ", quantityBedId=" + quantityBedId +
                ", categoryRoomId=" + categoryRoomId +
                ", floor=" + floor +
                ", dayPrice=" + dayPrice +
                '}';
    }
}
