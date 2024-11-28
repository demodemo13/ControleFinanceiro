
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
    //Consulta transações baseado no tipo
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
    
    //Alterar cadastro da transação
    public void alterar(String tipo, String descricao, String valor, String data, String id) {
        String query = "UPDATE tb_registro SET tipo = ?, descricao = ?, valor = ?,data = ? WHERE id = ?";

        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, tipo);
            stmt.setString(2, descricao);
            stmt.setString(3, valor);
            stmt.setString(4, data);
            stmt.setString(5, id);

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
            stmt.setString(1,id);
            
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
    
    //Gera relatório
    public String relatorio(String dataInicial, String dataFinal){        
        String query = "SELECT descricao, valor, data FROM tb_registro WHERE data BETWEEN ? AND ?";
        
        StringBuilder resultado = new StringBuilder();
        
        double valorTotal = 0;
        
        try(PreparedStatement stmt = conn.prepareStatement(query)){
            
            stmt.setString(1,dataInicial);
            stmt.setString(2,dataFinal);
            
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                String descricao = rs.getString("descricao");
                String valor = rs.getString("valor");
                double v = Double.parseDouble(valor);                
                valorTotal += v;
                String data = rs.getString("data");
                
                resultado.append("Descrição:").append(descricao).append(", Valor:").append(", Data:").append(data).append("\n");
            }
            JOptionPane.showMessageDialog(null,"Relatório Gerado com Sucesso.");
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null,"Erro:"+e.getMessage());
        }
        resultado.append("Valor Total:R$").append(valorTotal);
        return resultado.toString();
    }
}
