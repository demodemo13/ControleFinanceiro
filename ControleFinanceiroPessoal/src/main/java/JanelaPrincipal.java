
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class JanelaPrincipal extends JFrame {
    
    private JTextField tipo;
    private JTextField descricao;
    private JTextField valor;
    private JTextField data;
    private JTextField id;
    private TransacoesDAO transacoesDAO;
    private JTextArea resultadoArea;
    private JTextField dataInicial;
    private JTextField dataFinal;
    
    public JanelaPrincipal(TransacoesDAO transacoesDAO) {
        this.transacoesDAO = transacoesDAO;
        
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
        resultadoArea.setFocusable(false);
        dataInicial = new JTextField(10);
        dataFinal = new JTextField(10);
        
        //Botões
        JButton consultarButton = new JButton("Consultar");
        JButton cadastrarButton = new JButton("Cadastrar");
        JButton atualizarButton = new JButton("Atualizar");
        JButton excluirButton = new JButton("Excluir");
        JButton relatorioButton = new JButton("Gerar Relatório");
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
        add(new JLabel("Relatório "));
        add(new JLabel("Data Inicial: "));
        add(dataInicial);
        add(new JLabel("Data Final: "));
        add(dataFinal);
        add(relatorioButton);
        
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
        
        relatorioButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                relatorio();
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
    
    private void relatorio(){
        String dataIni = dataInicial.getText();
        String dataFin = dataFinal.getText();
        
        String resultado = transacoesDAO.relatorio(dataIni, dataFin);
        
        resultadoArea.setText("RELATÓRIO"+"\n"+resultado);
    }
}
