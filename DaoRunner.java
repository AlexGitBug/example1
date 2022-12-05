import dao.*;
import entity.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class DaoRunner {

    public static void main(String[] args) {

        var orderDao = OrderDao.getInstance();
        var order = orderDao.findById(4);
        System.out.println(order);
//saveUserInfo();
         saveOrder();
//
//        var instance = RoomDao.getInstance();
//        var room = new Room();
//        room.setNumberRoom(3);
//                room.setQuantityId(2);
//                room.setCategoryId(1);
//                room.setFloor(2);
//                room.setDayPrice(BigDecimal.valueOf(100.00));
//
//        instance.save(room);


    }

    private static void updateQuantityBed() {
        var quantityBedDao = QuantityBedDao.getInstance();
        var qua = quantityBedDao.findById(1);
        System.out.println(qua);
        qua.ifPresent(quantityBed -> {
            quantityBed.setCapacity("Двухместный номер");
            quantityBedDao.update(quantityBed);
        });
    }

    private static void orderUpdate() {
        var orderDao = OrderDao.getInstance();
        var maybeOrder = orderDao.findById(1);
        maybeOrder.ifPresent(order -> {
            order.setStatus("Свободно");
            order.setMessage("На рассмотрении");
            orderDao.update(order);
        });
    }

    private static void saveOrder() {
        var userInfo = new UserInfo();
        var room = new Room();
        var orderDao = OrderDao.getInstance();
        var order = new Order();
        order.setUserInfo(new UserInfo(1, "Sveta", "Svetikova", "100@gmail.com", 1400, "smetana", 2));
        order.setRoom(new Room(1,1,1,2,1, BigDecimal.valueOf(150)));
        order.setBeginTimeOfTheOrder(LocalDateTime.of(2021,12,11,05,10,00));
        order.setEndTimeOfTheOrder(LocalDateTime.of(2021,12,12,05,15,00));
        order.setStatus("Reserved");
        order.setMessage("In Processing");
        orderDao.save(order);
    }

    private static void saveUserInfo() {
        var userInfoDao = UserInfoDao.getInstance();
        var userInfo = new UserInfo();
        userInfo.setFirstName("Alex");
        userInfo.setLastName("Nesterov");
        userInfo.setEmail("321@gmail.com");
        userInfo.setAmount(150);
        userInfo.setPassword("kefir");
        userInfo.setRoleId(2);
        userInfoDao.save(userInfo);
    }


//        var maybeOrder = orderDao.findById(1);
//        maybeOrder.ifPresent(order -> {
//            order.setStatus("согласовано");
//            orderDao.update(order);
//        });








    private static void RoomDaoTestSave() {
        var roomDao = RoomDao.getInstance();
        var room = new Room();
        room.setNumberRoom(1);
        room.setQuantityBedId(2);
        room.setCategoryRoomId(2);
        room.setFloor(2);
        room.setDayPrice(BigDecimal.valueOf(100));
        roomDao.save(room);
    }

    private static void UserInfoTest() {
        var userInfoDao = UserInfoDao.getInstance();
        var userInfo = new UserInfo();
        userInfo.setFirstName("Petr");
        userInfo.setLastName("Petrov");
        userInfo.setEmail("123@mail.ru");
        userInfo.setPassword("123");
        userInfo.setRoleId(2);
        userInfoDao.save(userInfo);
    }

    private static void selectTest() {
        var roomDao = RoomDao.getInstance();
        var rooms = roomDao.findAll();
        System.out.println(rooms);
    }

    private static void updateTest() {
        var roomDao = RoomDao.getInstance();
        var maybeRoom = roomDao.findById(4);
        System.out.println(maybeRoom);

        maybeRoom.ifPresent(room -> {
            room.setDayPrice(BigDecimal.valueOf(200.8));
            roomDao.update(room);
        });
    }

    private static void deleteTest() {
        var roomDao = RoomDao.getInstance();
        var deleteRoomDaoResult = roomDao.delete(4);
        System.out.println(deleteRoomDaoResult);
    }

    private static void saveTest() {
        var roomDao = RoomDao.getInstance();
        var room = new Room();
        room.setNumberRoom(123);
        room.setQuantityBedId(1);
        room.setCategoryRoomId(1);
        room.setFloor(2);
        room.setDayPrice(BigDecimal.valueOf(100));

        var savedRoom = roomDao.save(room);
        System.out.println(savedRoom);
    }
}
