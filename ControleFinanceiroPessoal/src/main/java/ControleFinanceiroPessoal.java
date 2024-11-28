
import javax.swing.*;
import java.sql.Connection;
public class ControleFinanceiroPessoal {

    public static void main(String[] args) {
        Connection conn = null;
        
        //Conecta com Banco de Dados
        ConectaBanco cb = new ConectaBanco("jdbc:mysql://localhost:3306/bd_transacoes", "root", "", "com.mysql.cj.jdbc.Driver");
        
        conn = cb.getConnection();
        
        if(conn!=null){
            TransacoesDAO transacoesDAO = new TransacoesDAO(conn);
            
            new JanelaPrincipal(transacoesDAO);
        } else {
            JOptionPane.showMessageDialog(null,"Falha ao conectar com o Banco de Dados.");
        }
    }
}
