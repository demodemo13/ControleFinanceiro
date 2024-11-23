
import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class TransacoesDAO {

    //Atributos
    private Connection conn;

    //Construtor
    public TransacoesDAO(Connection conn) {
        this.conn = conn;
    }
    //Cadastra a transação
    public void inserir(String tipo, String descricao, String valor, String data) {
        String query = "INSERT INTO tb_registro(tipo, descricao, valor, data) VALUES(?,?,?,?)";

        //Formata a data para String
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, tipo);
            stmt.setString(2, descricao);
            stmt.setString(3, valor);
            stmt.setString(4, data);
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Transação cadastrada com sucesso.");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro:" + e.getMessage());
        }
    }
    //Consulta transações baseadp no tipo
    public String consultarTipo(String tipo) {
        String query = "SELECT * FROM tb_registro WHERE tipo = ?";

        StringBuilder resultado = new StringBuilder();

        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, tipo);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                String descricao = rs.getString("descricao");
                String valor = rs.getString("valor");
                String data = rs.getString("data");

                resultado.append("Tipo:").append(tipo).append(", Descrição:").append(descricao).append(", Valor:").append(valor).append(", Data:").append(data).append("\n");
            }

            if (resultado.length() == 0) {
                resultado.append("Nenhuma transação do típo especificado econtrada.");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro: " + e.getMessage());
        }
        return resultado.toString();
    }
    //Consulta trasações baseado na data
    public String consultarData(String data) {
        String query = "SELECT * FROM tb_registro WHERE tipo = ?";

        StringBuilder resultado = new StringBuilder();

        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(4, data);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                String tipo = rs.getString("tipo");
                String valor = rs.getString("valor");
                String descricao = rs.getString("descricao");

                resultado.append("Tipo:").append(tipo).append(", Descrição:").append(descricao).append(", Valor:").append(valor).append(", Data:").append(data).append("\n");
            }

            if (resultado.length() == 0) {
                resultado.append("Nenhuma transação do típo especificado econtrada.");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro: " + e.getMessage());
        }
        return resultado.toString();
    }
    //Alterar cadastro da transação
    public void alterar(String tipo, String descricao, String valor, String data) {
        String query = "UPDATE tb_registro SET tipo = ?, descricao = ?, valor = ?,data = ? WHERE id = ?";

        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, tipo);
            stmt.setString(2, descricao);
            stmt.setString(3, valor);
            stmt.setString(4, data);

            int rowsAffected = stmt.executeUpdate();

            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(null, "Transação alterada com sucesso.");
            } else {
                JOptionPane.showMessageDialog(null, "Transação não encontrada.");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro:" + e.getMessage());
        }
    }
    //Exclui cadastro da transação
    public void excluir(String id){
        String query = "DELETE FROM tb_registro WHERE id = ?";
        
        try(PreparedStatement stmt = conn.prepareStatement(query)){
            String idDeletar = "1";
            stmt.setString(1,idDeletar);
            
            int rowsAffected = stmt.executeUpdate();
            if(rowsAffected > 0){
                JOptionPane.showMessageDialog(null,"Transação excluída com sucesso.");
            }else{
                JOptionPane.showMessageDialog(null,"Transação não identificada.");
            }
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null,"Erro:"+e.getMessage());
        }
    }
}
