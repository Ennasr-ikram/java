package org.example.dao.Imp;

import org.example.dao.BoutiqueDao;
import org.example.entities.Boutique;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BoutiqueDaoImpl implements BoutiqueDao {
    private Connection connection= DB.getConnection();
    public void insert(Boutique boutique ) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                "INSERT INTO Boutique (nom, adress, chifaff, tel, description) VALUES (?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, boutique.getnom());
            preparedStatement.setString(2, boutique.getadress());
            preparedStatement.setInt(3, boutique.getnbvend());
            preparedStatement.setString(4, boutique.gettel());
            preparedStatement.setString(5, boutique.getdescription().toString());

            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                ResultSet rs = preparedStatement.getGeneratedKeys();

                if (rs.next()) {
                    int id = rs.getInt(1);

                    boutique.setId(id);
                }

                DB.closeResultSet(rs);
            } else {
                System.out.println("Aucune ligne renvoyée");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void update(Boutique boutique) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                "UPDATE Boutique SET nom = ?, adress = ?, chifaff = ?, tel = ?, description = ? WHERE id = ?")) {
            preparedStatement.setString(1, boutique.getnom());
            preparedStatement.setString(2, boutique.getadress());
            preparedStatement.setInt(3, boutique.getnbvend());
            preparedStatement.setString(4, boutique.gettel());
            preparedStatement.setString(5, boutique.getdescription().toString());
            preparedStatement.setInt(6, boutique.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteById(Integer id) {
        try (PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM Boutique WHERE id = ?")) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Boutique findById(Integer id) {
        Boutique boutique = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM Boutique WHERE id = ?")) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                boutique = new Boutique();
                boutique.setId(resultSet.getInt("id"));
                boutique.setnom(resultSet.getString("nom"));
                boutique.setadress(resultSet.getString("adress"));
                boutique.setnbvend(resultSet.getInt("chifaff"));
                boutique.settel(resultSet.getString("tel"));
                boutique.setdescription(resultSet.getString("description"));

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return boutique;
    }

    @Override
    public List<Boutique> findAll() {
        List<Boutique> boutiques = new ArrayList<>();
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM boutique");
            while (resultSet.next()) {
                Boutique boutique = new Boutique();
                boutique.setId(resultSet.getInt("id"));
                boutique.setnom(resultSet.getString("nom"));
                boutique.setadress(resultSet.getString("adress"));
                boutique.setnbvend(resultSet.getInt("chifaff"));
                boutique.settel(resultSet.getString("tel"));
                boutique.setdescription(resultSet.getString("description"));

                boutiques.add(boutique);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return boutiques;
    }

    @Override
    public Boutique findByName(String name) {

        return null;
    }


}
