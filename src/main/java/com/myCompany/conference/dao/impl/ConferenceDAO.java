package com.myCompany.conference.dao.impl;


import com.myCompany.conference.dao.factory.DAOFactory;
import com.myCompany.conference.dao.factory.impl.MySqlDAOFactory;
import com.myCompany.conference.entity.Conference;
import com.myCompany.conference.entity.Review;
import com.myCompany.conference.exception.ApplicationException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ConferenceDAO extends AbstractDAO<Conference> {
    private static final String SQL_INSERT = "INSERT INTO conference(id, title, time_conduction, venue) " +
            "VALUES(?, ?, ?, ?)";
    private static final String SQL_FIND_ALL = "SELECT * FROM conference";
    private static final String SQL_UPDATE = "UPDATE conference SET title = ?, time_conduction = ?, venue = ? " +
            "WHERE id = ?";
    private static final String SQL_DELETE = "DELETE FROM conference WHERE id = ?";

    private DAOFactory parentFactory = MySqlDAOFactory.getInstance();
    private AbstractDAO<Review> reviewDAO = parentFactory.createReview(connection);

    public ConferenceDAO(Connection connection) {
        super(connection);
    }

    @Override
    public List<Conference> findAll(){
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(SQL_FIND_ALL);

            return parseSet(resultSet);
        } catch (SQLException e) {
           throw new ApplicationException("Can't execute db command: " + e.getMessage(), e);
        }
    }

    @Override
    public Conference findById(long id) {
        if(isExistSpeaker(id)) {
            return findByLong("id", id).get(0);
        }
        return null;
    }
    private boolean isExistSpeaker(long id){
        return !findByLong("id", id).isEmpty();
    }

    @Override
    public List<Conference> findByString(String type, String value) {
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
    public List<Conference> findByLong(String type, long value) {
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
    public boolean create(Conference object) {
        try(PreparedStatement preparedStatement = connection.prepareStatement(SQL_INSERT)) {
            preparedStatement.setLong(1, object.getId());
            preparedStatement.setString(2, object.getTitle());
            preparedStatement.setTimestamp(3, object.getTimeConduction());
            preparedStatement.setString(4, object.getVenue());

            preparedStatement.execute();

            createReview(object.getReviewList(), object.getId());

            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Conference update(Conference object) {
        try(PreparedStatement preparedStatement = connection.prepareStatement(SQL_UPDATE)){
            preparedStatement.setString(1, object.getTitle());
            preparedStatement.setTimestamp(2, object.getTimeConduction());
            preparedStatement.setString(3, object.getVenue());
            preparedStatement.setLong(4, object.getId());

            preparedStatement.execute();

            updateReview(object.getReviewList(), object.getId());

            return object;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean delete(Conference object) {
        return delete(object.getId());
    }

    @Override
    public boolean delete(long id) {
        try(PreparedStatement preparedStatement = connection.prepareStatement(SQL_DELETE)) {
            preparedStatement.setLong(1, id);

            preparedStatement.execute();

            deleteReview(id);

            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    private List<Conference> parseSet(ResultSet resultSet) throws SQLException {
        List<Conference> conferenceList = new ArrayList<>();

        while(resultSet.next()){
            conferenceList.add(fillSpeaking(resultSet));
        }

        return conferenceList;
    }
    private Conference fillSpeaking(ResultSet resultSet) throws SQLException{
        Conference tempConference = new Conference();

        tempConference.setId(resultSet.getLong("id"));
        tempConference.setTitle(resultSet.getString("title"));
        tempConference.setTimeConduction(resultSet.getTimestamp("time_conduction"));
        tempConference.setVenue(resultSet.getString("venue"));
        tempConference.setReviewList(reviewDAO.findByLong("id_conference", resultSet.getLong("id")));

        return tempConference;
    }
    private String getSelectQuery(String type){return SQL_FIND_ALL + " WHERE " + type + " = ?";}

    private void createReview(List<Review> reviewList, long speakingId){
        for(Review review : reviewList) {
            reviewDAO.create(review);
            ((ReviewDAO)reviewDAO).updateConferenceId(review, speakingId);
        }
    }
    private void updateReview(List<Review> reviewList, long speakerId){
        deleteReview(speakerId);
        createReview(reviewList, speakerId);
    }
    private void deleteReview(long speakingId){
        List<Review> reviewList = reviewDAO.findByLong("id_conference", speakingId);

        for(Review review : reviewList){
            reviewDAO.delete(review);
        }
    }

}
