package View;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import Controller.Control;
import Model.ContatosBEAN;

public class Principal_Menu_{

    static Control controle = new Control();
    static ArrayList<ContatosBEAN> listaContatos = new ArrayList<ContatosBEAN>();

    public static Date convert_StringToDate(String dataStr) {
        java.sql.Date data = null;
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        try {
            data = new java.sql.Date(format.parse(dataStr).getTime());
        } catch (ParseException e) {
// TODO Auto-generated catch block
            e.printStackTrace();
        }
        return data;
    }

    private static void inserir() {
        String nome, apelido, data;
        Date datanascimento = null;
        Scanner teclado = new Scanner(System.in);
        System.out.println("Entre com o nome do Contato: ");
        nome = teclado.nextLine();
        System.out.println("Entre com o apelido do Contato: ");
        apelido = teclado.nextLine();
        System.out.println("Entre com a data de Nascimento do Contato: ");
        data = teclado.nextLine();
        datanascimento = convert_StringToDate(data);
        ContatosBEAN contato = new ContatosBEAN(0, nome, apelido, datanascimento);
        controle.addContato(contato);
    }

    private static void alterar() {
        Integer id;
        String nome, apelido, data, opcao;
        Date datanascimento = null;
        Scanner teclado = new Scanner(System.in);
        System.out.println("Entre com Id do Contato: ");
        id = teclado.nextInt();
        if (controle.isExist(id)) {
//TODO – A fazer
        } else {
            System.out.println("Contato não Existe !");
        }
    }

    private static void listar() {
        List<ContatosBEAN> listaContatos = controle.listaContatos();
        for (ContatosBEAN contato : listaContatos) {
            System.out.print("NOME: ");
            System.out.println(contato.getNome());
            System.out.print("APELIDO: ");
            System.out.println(contato.getApelido());
            System.out.print("NASCIMENTO: ");
            System.out.println(contato.getData_nascimento());
        }
    }

    public static void main(String[] args) {
        int opcao = 1;
        Scanner numerico = new Scanner(System.in);
        while (opcao != 0) {
            System.out.println("1 - Inserir | 2 - Alterar | 3 - Exluir | 4 - Consultar | 5 - Listar | 0 - Sair");
            opcao = numerico.nextInt();
            switch (opcao) {
                case 1:
                    inserir();
                    break;
                case 2:
                    alterar();
                    break;
                case 5:
                    listar();
                    break;
            }
        }
    }
}
