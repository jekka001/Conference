package com.myCompany.conference.dao.impl;


import com.myCompany.conference.dao.factory.DAOFactory;
import com.myCompany.conference.dao.factory.impl.MySqlDAOFactory;
import com.myCompany.conference.entity.Review;
import com.myCompany.conference.entity.Speaker;
import com.myCompany.conference.entity.StateReview;
import com.myCompany.conference.entity.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReviewDAO extends AbstractDAO<Review> {
    private static final String SQL_INSERT = "INSERT INTO review(id, topic, id_speaker, registered, visitors, state) " +
            "VALUES(?, ?, ?, ?, ?, ?)";
    private static final String SQL_INSERT_REVIEW_USER = "INSERT INTO registered(id_user, id_review) VALUE(?, ?)";
    private static final String SQL_FIND_ALL = "SELECT * FROM review";
    private static final String SQL_FIND_USER = "SELECT * FROM registered WHERE id_review = ?";
    private static final String SQL_UPDATE = "UPDATE review SET topic = ?, id_speaker = ?, registered = ?, visitors = ?, state = ?" +
            "WHERE id = ?";
    private static final String SQL_UPDATE_CONFERENCE_ID = "UPDATE review SET id_conference = ? " +
            "WHERE id = ?";
    private static final String SQL_DELETE = "DELETE FROM review WHERE id = ?";
    private static final String SQL_DELETE_USER ="DELETE FROM registered WHERE id_review = ?";

    private DAOFactory parentFactory = MySqlDAOFactory.getInstance();
    private AbstractDAO<Speaker> speakerDAO = parentFactory.createSpeaker(connection);
    private AbstractDAO<User> userDAO = parentFactory.createUser(connection);

    public ReviewDAO(Connection connection) {
        super(connection);
    }

    @Override
    public List<Review> findAll() {
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(SQL_FIND_ALL);

            return parseSet(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Review findById(long id) {
        if(isExistReview(id)) {
            return findByLong("id", id).get(0);
        }
        return null;
    }
    private boolean isExistReview(long id){
        return !findByLong("id", id).isEmpty();
    }

    @Override
    public List<Review> findByString(String type, String value) {
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
    public List<Review> findByLong(String type, long value) {
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
    public boolean create(Review object) {
        try(PreparedStatement preparedStatement = connection.prepareStatement(SQL_INSERT)) {
            preparedStatement.setLong(1, object.getId());
            preparedStatement.setString(2, object.getTopic());
            preparedStatement.setLong(3, object.getSpeaker().getId());
            preparedStatement.setInt(4, object.getIntRegistered());
            preparedStatement.setInt(5, object.getVisitors());
            preparedStatement.setString(6, object.getState().toString());

            preparedStatement.execute();

            createUser(object.getRegistered(), object.getId());

            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Review update(Review object) {
        try(PreparedStatement preparedStatement = connection.prepareStatement(SQL_UPDATE)){
            preparedStatement.setString(1, object.getTopic());
            preparedStatement.setLong(2, object.getSpeaker().getId());
            preparedStatement.setInt(3, object.getIntRegistered());
            preparedStatement.setInt(4, object.getVisitors());
            preparedStatement.setString(5,object.getState().toString());
            preparedStatement.setLong(6, object.getId());

            preparedStatement.execute();

            updateUser(object.getRegistered(), object.getId());

            return object;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean delete(Review object) {
        return delete(object.getId());
    }

    @Override
    public boolean delete(long id) {
        deleteUser(id);
        try(PreparedStatement preparedStatement = connection.prepareStatement(SQL_DELETE)) {
            preparedStatement.setLong(1, id);

            preparedStatement.execute();


            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    private List<Review> parseSet(ResultSet resultSet) throws SQLException {
        List<Review> reviewList = new ArrayList<>();

        while(resultSet.next()){
            reviewList.add(fillReview(resultSet));
        }

        return reviewList;
    }
    private Review fillReview(ResultSet resultSet) throws SQLException{
        Review tempReview = new Review();

        tempReview.setId(resultSet.getLong("id"));
        tempReview.setTopic(resultSet.getString("topic"));
        tempReview.setSpeaker(speakerDAO.findById(resultSet.getLong("id_speaker")));
        tempReview.setRegistered(findUser(tempReview.getId()));
        tempReview.setVisitors(resultSet.getInt("visitors"));
        tempReview.setState(StateReview.valueOf(resultSet.getString("state")));

        return tempReview;
    }

    private String getSelectQuery(String type){return SQL_FIND_ALL + " WHERE " + type + " = ?";}

    private List<User> findUser(long idReview){
        try(PreparedStatement preparedStatement = connection.prepareStatement(SQL_FIND_USER)) {
            preparedStatement.setLong(1, idReview);

            ResultSet resultSet = preparedStatement.executeQuery();

            return parseSetForUser(resultSet);
        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }
    private List<User> parseSetForUser(ResultSet resultSet) throws SQLException{
        List<User> userList = new ArrayList<>();

        while(resultSet.next()){
            userList.add(userDAO.findById(resultSet.getLong("id_user")));
        }

        return userList;
    }
    private void createUser(List<User> userList, long idReview){
        for (User user : userList) {
            createReviewUserId(idReview, user.getId());
        }
    }
    public void createReviewUserId(long idReview, long idUser){
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_INSERT_REVIEW_USER)) {
            preparedStatement.setLong(1, idUser);
            preparedStatement.setLong(2, idReview);

            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    private void updateUser(List<User> userList, long idReview){
        deleteUser(idReview);
        createUser(userList, idReview);
    }
    private void deleteUser(long idReview){
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_DELETE_USER)) {
            preparedStatement.setLong(1, idReview);
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean updateConferenceId(Review review, long conferenceId){
        try(PreparedStatement preparedStatement = connection.prepareStatement(SQL_UPDATE_CONFERENCE_ID)) {
            preparedStatement.setLong(1, conferenceId);
            preparedStatement.setLong(2, review.getId());

            preparedStatement.execute();

            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
