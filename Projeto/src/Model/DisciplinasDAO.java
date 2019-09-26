package Model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DisciplinasDAO {
    private static DisciplinasDAO instance;

    private DisciplinasDAO() {
        MySQLDAO.getConnection();
    }

    public static DisciplinasDAO getInstance() {
        if (instance == null) {
            instance = new DisciplinasDAO();
        }
        return instance;
    }

    public long create(DisciplinasBEAN disciplina) {
        String query = "INSERT INTO tblDisciplinas (nome, status = True) VALUES (?)";
        return MySQLDAO.executeQuery(query, disciplina.getNome());
    }

    public void update(DisciplinasBEAN disciplina) {
        String query = "UPDATE tblDisciplinas SET nome=?, status=? WHERE id = ?";
        MySQLDAO.executeQuery(query, disciplina.getNome(), disciplina.getStatus(), disciplina.getId());
    }

    public void delete(DisciplinasBEAN disciplina) {
        MySQLDAO.executeQuery("UPDATE tblDisciplinas SET status = ? WHERE id = ?", disciplina.getStatus(), disciplina.getId());
    }

    public ArrayList<DisciplinasBEAN> findAllPessoa() {
        return listaDisciplinas("SELECT * FROM Disciplinas ORDER BY Nome");
    }

    public ArrayList<DisciplinasBEAN> listaDisciplinas(String query) {
        ArrayList<DisciplinasBEAN> lista = new ArrayList<DisciplinasBEAN>();
        ResultSet rs = null;
        rs = MySQLDAO.getResultSet(query);
        try {
            while (rs.next()) {
                lista.add(new DisciplinasBEAN(rs.getInt("id"), rs.getString("nome"), rs.getBoolean("status")));
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    public DisciplinasBEAN findContato(int id) {
        DisciplinasBEAN result = null;
        ResultSet rs = null;
        rs = MySQLDAO.getResultSet("SELECT * FROM Disciplinas WHERE id=?", id);
        try {
            if (rs.next()) {
                result = new DisciplinasBEAN(rs.getInt("id"), rs.getString("nome"), rs.getBoolean("status"));
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public int findId(DisciplinasBEAN disciplina) {
        int result = 0;
        ResultSet rs = null;
        rs = MySQLDAO.getResultSet(
        "SELECT * FROM Disciplinas WHERE nome= ? and status= ?",disciplina.getNome(),disciplina.getStatus());
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
        rs = MySQLDAO.getResultSet("SELECT * FROM Disciplinas WHERE id= ?", id);
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
