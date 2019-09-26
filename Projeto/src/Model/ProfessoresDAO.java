package Model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ProfessoresDAO {
    private static ProfessoresDAO instance;

    private ProfessoresDAO() {
        MySQLDAO.getConnection();
    }

    public static ProfessoresDAO getInstance() {
        if (instance == null) {
            instance = new ProfessoresDAO();
        }
        return instance;
    }

    public long create(ProfessoresBEAN professor) {
        String query = "INSERT INTO tblProfessores (nome, status) VALUES (?,?)";
        return MySQLDAO.executeQuery(query, professor.getNome(), '1');
    }

    public void update(ProfessoresBEAN professor) {
        String query = "UPDATE tblProfessores SET nome=?, status=? WHERE id = ?";
        MySQLDAO.executeQuery(query, professor.getNome(), professor.getStatus(), professor.getId());
    }

    public void delete(ProfessoresBEAN professor) {
        MySQLDAO.executeQuery("UPDATE tblProfessores SET status = 0 WHERE id = ?", professor.getId());
    }

    public ArrayList<ProfessoresBEAN> findAllProfessores() {
        return listaprofessors("SELECT * FROM tblProfessores ORDER BY nome");
    }

    public ArrayList<ProfessoresBEAN> listaprofessors(String query) {
        ArrayList<ProfessoresBEAN> lista = new ArrayList<ProfessoresBEAN>();
        ResultSet rs = null;
        rs = MySQLDAO.getResultSet(query);
        try {
            while (rs.next()) {
                lista.add(new ProfessoresBEAN(rs.getInt("id"), rs.getString("nome"), rs.getInt("status")));
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    public ProfessoresBEAN findprofessor(int id) {
        ProfessoresBEAN result = null;
        ResultSet rs = null;
        rs = MySQLDAO.getResultSet("SELECT * FROM tblProfessores WHERE id=?", id);
        try {
            if (rs.next()) {
                result = new ProfessoresBEAN(rs.getInt("id"), rs.getString("nome"), rs.getInt("status"));
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public int findId(ProfessoresBEAN professor) {
        int result = 0;
        ResultSet rs = null;
        rs = MySQLDAO.getResultSet(
        "SELECT * FROM tblProfessores WHERE nome= ? and status= ?",professor.getNome(),professor.getStatus());
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
        rs = MySQLDAO.getResultSet("SELECT * FROM tblProfessores WHERE id= ?", id);
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
