package Model;
    
import java.util.Date;

public class ContatosBEAN {

    private int id;
    private String nome;
    private String apelido;
    private Date data_nascimento;

    public ContatosBEAN(int id, String nome, String apelido, Date data_nascimento) {
        this.id = id;
        this.nome = nome;
        this.apelido = apelido;
        this.data_nascimento = data_nascimento;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getApelido() {
        return apelido;
    }

    public void setApelido(String apelido) {
        this.apelido = apelido;
    }

    public Date getData_nascimento() {
        return data_nascimento;
    }

    public void setData_nascimento(Date data_nascimento) {
        this.data_nascimento = data_nascimento;
    }

    public int getId() {
        return id;
    }
}
