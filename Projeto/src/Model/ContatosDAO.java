package Model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ContatosDAO {

    private static ContatosDAO instance;

    private ContatosDAO() {
        MySQLDAO.getConnection();
    }

    public static ContatosDAO getInstance() {
        if (instance == null) {
            instance = new ContatosDAO();
        }
        return instance;
    }

    public long create(ContatosBEAN contato) {
        String query = "INSERT INTO CONTATOS (nome, apelido, data_nascimento) VALUES (?,?,?)";
        return MySQLDAO.executeQuery(query, contato.getNome(), contato.getApelido(), contato.getData_nascimento());
    }

    public void update(ContatosBEAN contato) {
        String query = "UPDATE CONTATOS SET nomeo=?, apelido=?, data_nascimento=? WHERE id = ?";
        MySQLDAO.executeQuery(query, contato.getNome(), contato.getApelido(),
                contato.getData_nascimento(), contato.getId());
    }

    public void delete(ContatosBEAN contato) {
        MySQLDAO.executeQuery("DELETE FROM CONTATOS WHERE id = ?", contato.getId());
    }

    public ArrayList<ContatosBEAN> findAllPessoa() {
        return listaContatos("SELECT * FROM Contatos ORDER BY Nome");
    }

    public ArrayList<ContatosBEAN> listaContatos(String query) {
        ArrayList<ContatosBEAN> lista = new ArrayList<ContatosBEAN>();
        ResultSet rs = null;
        rs = MySQLDAO.getResultSet(query);
        try {
            while (rs.next()) {
                lista.add(new ContatosBEAN(rs.getInt("id"), rs.getString("nome"), rs.getString("apelido"),
                        rs.getDate("data_nascimento")));
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    public ContatosBEAN findContato(int id) {
        ContatosBEAN result = null;
        ResultSet rs = null;
        rs = MySQLDAO.getResultSet("SELECT * FROM Contatos WHERE id=?", id);
        try {
            if (rs.next()) {
                result = new ContatosBEAN(rs.getInt("id"), rs.getString("nome"), rs.getString("apelido"),
                        rs.getDate("data_nascimento"));
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public int findId(ContatosBEAN contato) {
        int result = 0;
        ResultSet rs = null;
        rs = MySQLDAO.getResultSet(
        "SELECT * FROM Contatos WHERE nome= ? and apelido= ? and data_nascimento =  ?",contato.getNome(),contato.getApelido(),contato.getData_nascimento());
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
        rs = MySQLDAO.getResultSet("SELECT * FROM Contatos WHERE id= ?", id);
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
