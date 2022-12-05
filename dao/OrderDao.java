package dao;

import entity.Order;
import entity.Room;
import entity.UserInfo;
import exception.DaoException;
import util.ConnectionManager;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class OrderDao {
    private static final OrderDao INSTANCE = new OrderDao();
    private static final String DELETE_SQL = """
            DELETE FROM orders
            WHERE id  = ?
            """;
    private static final String SAVE_SQL = """
            INSERT INTO orders (user_info_id, room_id, begin_time, end_time, status, message)
            VALUES (?, ?, ?, ?, ?, ?)
            """;

    private static final String UPDATE_SQL = """
            UPDATE orders
            SET user_info_id = ?,
                room_id = ?,
                begin_time = ?,
                end_time = ?,
                status = ?,
                message = ?
            WHERE id = ?
            """;
    private static final String FIND_ALL_SQL = """
            SELECT orders.id,
                   user_info_id,
                  begin_time,
                   end_time,
                  status, 
                  message,
                  ui.first_name,
                  ui.last_name,
                  ui.email,
                  ui.amount,
                  ui.password,
                  ui.role_id,
                  r.number_room,
                  r.quantity_bed_id,
                  r.category_room_id,
                  r.floor,
                  r.day_price
                FROM orders
                JOIN user_info ui ON ui.id = orders.user_info_id
                JOIN room r on orders.room_id = r.id
            """;
    private static final String FIND_BY_ID_SQL = FIND_ALL_SQL + """
            WHERE orders.id = ?
            """;

    private OrderDao() {
    }

    public List<Order> findAll() {
        try (var connection = ConnectionManager.get();
             var preparedStatement = connection.prepareStatement(FIND_ALL_SQL)) {
            var resultSet = preparedStatement.executeQuery();
            List<Order> orders = new ArrayList<>();
            while (resultSet.next()) {
                orders.add(buildOrder(resultSet));
            }
            return orders;
        } catch (SQLException throwables) {
            throw new DaoException(throwables);
        }
    }

    public Optional<Order> findById(int id) {
        try (var connection = ConnectionManager.get();
             var preparedStatement = connection.prepareStatement(FIND_BY_ID_SQL)) {
            preparedStatement.setInt(1, id);

            var resultSet = preparedStatement.executeQuery();
            Order order = null;
            if (resultSet.next()) {
                order = buildOrder(resultSet);
            }
            return Optional.ofNullable(order);
        } catch (SQLException throwables) {
            throw new DaoException(throwables);
        }
    }

    private static Order buildOrder(ResultSet resultSet) throws SQLException {
        var userInfo = new UserInfo(
                resultSet.getInt("user_info_id"),
                resultSet.getString("first_name"),
                resultSet.getString("last_name"),
                resultSet.getString("email"),
                resultSet.getInt("amount"),
                resultSet.getString("password"),
                resultSet.getInt("role_id")
        );
        var room = new Room(
                resultSet.getInt("id"),
                resultSet.getInt("number_room"),
                resultSet.getInt("quantity_bed_id"),
                resultSet.getInt("category_room_id"),
                resultSet.getInt("floor"),
                resultSet.getBigDecimal("day_price")
        );
        return new Order(
                resultSet.getInt("id"),
                userInfo,
                room,
                resultSet.getTimestamp("begin_time").toLocalDateTime(),
                resultSet.getTimestamp("end_time").toLocalDateTime(),
                resultSet.getString("status"),
                resultSet.getString("message")
        );
    }

    public void update(Order order) {
        try (var connection = ConnectionManager.get();
             var preparedStatement = connection.prepareStatement(UPDATE_SQL)) {
            preparedStatement.setInt(1, order.getUserInfo().getId());
            preparedStatement.setInt(2, order.getRoom().getId());
            preparedStatement.setTimestamp(3, Timestamp.valueOf(order.getBeginTimeOfTheOrder()));
            preparedStatement.setTimestamp(4, Timestamp.valueOf(order.getEndTimeOfTheOrder()));
            preparedStatement.setString(5, order.getStatus());
            preparedStatement.setString(6, order.getMessage());
            preparedStatement.setInt(7, order.getId());

            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throw new DaoException(throwables);
        }
    }

    public Order save(Order order) {
        try (var connection = ConnectionManager.get();
             var preparedStatement = connection.prepareStatement(SAVE_SQL, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setInt(1, order.getUserInfo().getId());
            preparedStatement.setInt(2, order.getRoom().getId());
            preparedStatement.setTimestamp(3, Timestamp.valueOf(order.getBeginTimeOfTheOrder()));
            preparedStatement.setTimestamp(4, Timestamp.valueOf(order.getEndTimeOfTheOrder()));
            preparedStatement.setString(5, order.getStatus());
            preparedStatement.setString(6, order.getMessage());

            preparedStatement.executeUpdate();

            var generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                order.setId(generatedKeys.getInt("id"));
            }
            return order;
        } catch (SQLException throwables) {
            throw new DaoException(throwables);
        }
    }

    public boolean delete(int id) {
        try (Connection connection = ConnectionManager.get();
             var preparedStatement = connection.prepareStatement(DELETE_SQL)) {
            preparedStatement.setInt(1, id);

            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException throwables) {
            throw new DaoException(throwables);
        }
    }

    public static OrderDao getInstance() {
        return INSTANCE;
    }
}


