package entity;

import java.time.LocalDateTime;
import java.util.Objects;

public class Order {

    private int id;
    private UserInfo userInfo;
    private Room room;
    private LocalDateTime beginTimeOfTheOrder;
    private LocalDateTime endTimeOfTheOrder;
    private String status;
    private String message;

    public Order() {
    }

    public Order(int id, UserInfo userInfo, Room room, LocalDateTime beginTimeOfTheOrder, LocalDateTime endTimeOfTheOrder, String status, String message) {
        this.id = id;
        this.userInfo = userInfo;
        this.room = room;
        this.beginTimeOfTheOrder = beginTimeOfTheOrder;
        this.endTimeOfTheOrder = endTimeOfTheOrder;
        this.status = status;
        this.message = message;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public UserInfo getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public LocalDateTime getBeginTimeOfTheOrder() {
        return beginTimeOfTheOrder;
    }

    public void setBeginTimeOfTheOrder(LocalDateTime beginTimeOfTheOrder) {
        this.beginTimeOfTheOrder = beginTimeOfTheOrder;
    }

    public LocalDateTime getEndTimeOfTheOrder() {
        return endTimeOfTheOrder;
    }

    public void setEndTimeOfTheOrder(LocalDateTime endTimeOfTheOrder) {
        this.endTimeOfTheOrder = endTimeOfTheOrder;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return id == order.id && userInfo == order.userInfo && room == order.room && Objects.equals(beginTimeOfTheOrder, order.beginTimeOfTheOrder) && Objects.equals(endTimeOfTheOrder, order.endTimeOfTheOrder) && Objects.equals(status, order.status) && Objects.equals(message, order.message);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userInfo, room, beginTimeOfTheOrder, endTimeOfTheOrder, status, message);
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", userInfo=" + userInfo +
                ", room=" + room +
                ", beginTime=" + beginTimeOfTheOrder +
                ", endTime=" + endTimeOfTheOrder +
                ", status='" + status + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}
