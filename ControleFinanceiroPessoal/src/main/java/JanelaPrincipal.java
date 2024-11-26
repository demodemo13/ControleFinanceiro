
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;

public class JanelaPrincipal extends JFrame {

    private JanelaPrincipal janelaPrincipal;
    private JTextField tipo;
    private JTextField descricao;
    private JTextField valor;
    private JTextField data;
    private JTextField id;
    private TransacoesDAO transacoesDAO;
    private JTextArea resultadoArea;
    
    public JanelaPrincipal(TransacoesDAO transacoesDAO) {
        this.transacoesDAO = transacoesDAO;
        
        //Instância da janela JFrame        

        //Propriedades da Janela
        setSize(600, 400);//Configura o tamanho da janela
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());
        setLocationRelativeTo(null);

        //Campos de Entrada
        tipo = new JTextField(15);
        descricao = new JTextField(20);
        valor = new JTextField(20);
        data = new JTextField(10);
        id = new JTextField(20);
        resultadoArea = new JTextArea(10,30);
        resultadoArea.setEditable(false);
        //Botões
        JButton consultarButton = new JButton("Consultar");
        JButton cadastrarButton = new JButton("Cadastrar");
        JButton atualizarButton = new JButton("Atualizar");
        JButton excluirButton = new JButton("Excluir");
        //Adiciona componentes à tela
        add(new JLabel("Tipo:"));
        add(tipo);
        add(new JLabel("Descrição:"));
        add(descricao);
        add(new JLabel("Valor:"));
        add(valor);
        add(new JLabel("Data:"));
        add(data);
        add(new JLabel("ID(Insira ID do item no banco de dados para excluir ou atualizar):"));
        add(id);
        add(cadastrarButton);
        add(consultarButton);
        add(atualizarButton);
        add(excluirButton);        
        add(new JScrollPane(resultadoArea));
        
        //Ações dos botões
        consultarButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                consultar();
            }
        });
        
        cadastrarButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                cadastrar();
            }
        });
        
        atualizarButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                atualizar();
            }
        });
        
        excluirButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                excluir();
            }
        });
        
        
        setVisible(true);
    }
    
    private void consultar(){
        String tipoString = tipo.getText();
        String resultado = transacoesDAO.consultarTipo(tipoString);
        resultadoArea.setText(resultado);
    }
    
    private void cadastrar(){
        String tipoString = tipo.getText();
        String descricaoString = descricao.getText();
        String valorString = valor.getText();
        String dataString = data.getText();
        
        transacoesDAO.inserir(tipoString, descricaoString, valorString, dataString);
    }
    
    private void atualizar(){
        String tipoString = tipo.getText();
        String descricaoString = descricao.getText();
        String valorString = valor.getText();
        String dataString = data.getText();
        String idString = id.getText();
        if(idString.isEmpty()){
            JOptionPane.showMessageDialog(null, "Insira um id válido.");
        }else {
            transacoesDAO.alterar(tipoString, descricaoString, valorString, dataString,idString);
        }        
    }
    
    private void excluir(){
        String idString = id.getText();
        
        transacoesDAO.excluir(idString);
    }
}
