package com.myCompany.conference.dao.impl;

import com.myCompany.conference.dao.factory.DAOFactory;
import com.myCompany.conference.dao.factory.impl.MySqlDAOFactory;
import com.myCompany.conference.entity.Review;
import com.myCompany.conference.entity.Speaker;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SpeakerDAO extends AbstractDAO<Speaker> {
    private static final String SQL_INSERT = "INSERT INTO user(id, rating, bonus) " +
            "VALUES(?, ?, ?)";
    private static final String SQL_FIND_ALL = "SELECT * FROM user";
    private static final String SQL_UPDATE = "UPDATE user SET rating = ?, bonus = ? " +
            "WHERE id = ?";
    private static final String SQL_DELETE = "DELETE FROM user WHERE id = ?";


    public SpeakerDAO(Connection connection) {
        super(connection);
    }

    @Override
    public List<Speaker> findAll() {
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(SQL_FIND_ALL);

            return parseSet(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Speaker findById(long id) {
        if(isExistSpeaker(id)) {
            return findByLong("id", id).get(0);
        }
        return null;
    }
    private boolean isExistSpeaker(long id){
        return !findByLong("id", id).isEmpty();
    }

    @Override
    public List<Speaker> findByString(String type, String value) {
        String currentSql = getSelectQuery(type);

        try(PreparedStatement preparedStatement = connection.prepareStatement(currentSql)) {
            preparedStatement.setString(1, value);

            ResultSet resultSet = preparedStatement.executeQuery();

            return parseSet(resultSet);
        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Speaker> findByLong(String type, long value) {
        String currentSql = getSelectQuery(type);

        try(PreparedStatement preparedStatement = connection.prepareStatement(currentSql)) {
            preparedStatement.setLong(1, value);

            ResultSet resultSet = preparedStatement.executeQuery();

            return parseSet(resultSet);
        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean create(Speaker object) {
        try(PreparedStatement preparedStatement = connection.prepareStatement(SQL_INSERT)) {
            preparedStatement.setLong(1, object.getId());
            preparedStatement.setLong(2, object.getRating());
            preparedStatement.setLong(3, object.getBonus());

            preparedStatement.execute();

            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Speaker update(Speaker object) {
        try(PreparedStatement preparedStatement = connection.prepareStatement(SQL_UPDATE)){
            preparedStatement.setLong(1, object.getRating());
            preparedStatement.setLong(2, object.getBonus());
            preparedStatement.setLong(3, object.getId());

            preparedStatement.execute();

            return object;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean delete(Speaker object) {
        return delete(object.getId());
    }

    @Override
    public boolean delete(long id) {
        try(PreparedStatement preparedStatement = connection.prepareStatement(SQL_DELETE)) {
            preparedStatement.setLong(1, id);

            preparedStatement.execute();

            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    private List<Speaker> parseSet(ResultSet resultSet) throws SQLException {
        List<Speaker> speakerList = new ArrayList<>();

        while(resultSet.next()){
            speakerList.add(fillSpeaker(resultSet));
        }

        return speakerList;
    }
    private Speaker fillSpeaker(ResultSet resultSet) throws SQLException{
        Speaker tempSpeaker = new Speaker();

        tempSpeaker.setId(resultSet.getLong("id"));
        tempSpeaker.setRating(resultSet.getLong("rating"));
        tempSpeaker.setBonus(resultSet.getLong("bonus"));

        return tempSpeaker;
    }
    private String getSelectQuery(String type){return SQL_FIND_ALL + " WHERE " + type + " = ?";}
}
