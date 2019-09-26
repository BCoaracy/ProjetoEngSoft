package Model;

public class FaculdadesBEAN {
    private int id;
    private String nome;
    private int idDisciplina;
    private int status;

    public FaculdadesBEAN(int id, String nome, int idDisciplina, int status) {
        this.id = id;
        this.nome = nome;
        this.idDisciplina = idDisciplina;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getIdDisciplina() {
        return idDisciplina;
    }

    public void setIdDisciplina(int idDisciplina) {
        this.idDisciplina = idDisciplina;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
    
    
}
