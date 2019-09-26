package Model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class FaculdadesDAO {
    private static FaculdadesDAO instance;

    private FaculdadesDAO() {
        MySQLDAO.getConnection();
    }

    public static FaculdadesDAO getInstance() {
        if (instance == null) {
            instance = new FaculdadesDAO();
        }
        return instance;
    }

    public long create(FaculdadesBEAN faculdade) {
        String query = "INSERT INTO tblFaculdades (nome, status, idDisciplina) VALUES (?,?,?)";
        return MySQLDAO.executeQuery(query, faculdade.getNome(), '1', faculdade.getIdDisciplina());
    }

    public void update(FaculdadesBEAN faculdade) {
        String query = "UPDATE tblFaculdades SET nome=?, status=?, idDisciplina=? WHERE id = ?";
        MySQLDAO.executeQuery(query, faculdade.getNome(), faculdade.getStatus(), faculdade.getIdDisciplina(), faculdade.getId());
    }

    public void delete(FaculdadesBEAN faculdade) {
        MySQLDAO.executeQuery("UPDATE tblFaculdades SET status = 0 WHERE id = ?", faculdade.getId());
    }

    public ArrayList<FaculdadesBEAN> findAllfaculdadees() {
        return listafaculdades("SELECT * FROM tblFaculdades ORDER BY nome");
    }

    public ArrayList<FaculdadesBEAN> listafaculdades(String query) {
        ArrayList<FaculdadesBEAN> lista = new ArrayList<FaculdadesBEAN>();
        ResultSet rs = null;
        rs = MySQLDAO.getResultSet(query);
        try {
            while (rs.next()) {
                lista.add(new FaculdadesBEAN(rs.getInt("id"), rs.getString("nome"), rs.getInt("status"), rs.getInt("IdDisciplina")));
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    public FaculdadesBEAN findfaculdade(int id) {
        FaculdadesBEAN result = null;
        ResultSet rs = null;
        rs = MySQLDAO.getResultSet("SELECT * FROM tblFaculdades WHERE id=?", id);
        try {
            if (rs.next()) {
                result = new FaculdadesBEAN(rs.getInt("id"), rs.getString("nome"), rs.getInt("status"), rs.getInt("IdDisciplina"));
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public int findId(FaculdadesBEAN faculdade) {
        int result = 0;
        ResultSet rs = null;
        rs = MySQLDAO.getResultSet(
        "SELECT * FROM tblFaculdades WHERE nome= ? and status= ?",faculdade.getNome(),faculdade.getStatus());
try {
            if (rs.next()) {
                result = rs.getInt("id");
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public Boolean isExist(int id) {
        Boolean result = false;
        ResultSet rs = null;
        rs = MySQLDAO.getResultSet("SELECT * FROM tblFaculdades WHERE id= ?", id);
        try {
            if (rs.next()) {
                result = true;
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
}
