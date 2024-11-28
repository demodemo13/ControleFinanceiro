
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class ConectaBanco {
    //Atributos
    private final String url;
    private final String usuario;
    private final String senha;
    private final String drv;
    
    //Construtor
    public ConectaBanco(String url, String usuario, String senha, String drv){
        this.url = url;
        this.usuario = usuario;
        this.senha = senha;
        this.drv = drv;
    }
    //Método que válida conexão e confere erros de Driver e outros
    public Connection getConnection(){
        Connection conexao = null;
        
        try{
            Class.forName(drv);
            conexao = DriverManager.getConnection(url,usuario,senha);
            JOptionPane.showMessageDialog(null,"Conexão estabelecida com sucesso.");
        }catch(ClassNotFoundException e){
            JOptionPane.showMessageDialog(null,"Driver não encontrado:"+ e.getMessage());
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null,"Erro ao conectar ao banco de dados:" + e.getMessage());
        }
        return conexao;
    }
}
