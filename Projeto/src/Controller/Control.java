package Controller;

import Model.*;
import java.util.ArrayList;
/**
 *
 * @author bruno
 */
public class Control {

    public Control() {
    }

    public void addContato(ContatosBEAN contato) {
        ContatosDAO.getInstance().create(contato);
    }

    public void updateContato(ContatosBEAN contato) {
        ContatosDAO.getInstance().update(contato);
    }

    public void deleteContato(ContatosBEAN contato) {
        ContatosDAO.getInstance().delete(contato);
    }

    public ContatosBEAN findPessoa(int id) {
        return ContatosDAO.getInstance().findContato(id);
    }

    public int findIdContato(ContatosBEAN contato) {
        return ContatosDAO.getInstance().findId(contato);
    }

    public Boolean isExist(int id) {
        return ContatosDAO.getInstance().isExist(id);
    }

    public ArrayList<ContatosBEAN> listaContatos() {
        return ContatosDAO.getInstance().findAllPessoa();
    }
}
