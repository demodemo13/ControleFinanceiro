
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;

public class JanelaPrincipal extends JFrame {

    private JanelaPrincipal janelaPrincipal;
    private JTextField saldoTexto;

    public JanelaPrincipal() {

        //Instância da janela JFrame
        JFrame janela = new JFrame("Financeiro");

        //Propriedades da Janela
        janela.setSize(600, 400);//Configura o tamanho da janela
        janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        janela.setLayout(new FlowLayout());
        janela.setLocationRelativeTo(null);

        //Cria Campo de Texto para saldo
        JLabel saldoDisplay = new JLabel("Saldo:");

        JTextField saldoTexto = new JTextField("0,0");
        saldoTexto.setEditable(false);
        saldoTexto.setFocusable(false);

        //Configura painel
        JPanel painel = new JPanel();
        painel.setLayout(new FlowLayout());

        JButton adicionarSaldo = new JButton("Adicionar Saldo");

        //Adiciona o campo do saldo ao painel
        painel.add(saldoDisplay);
        painel.add(saldoTexto);
        painel.add(adicionarSaldo);

        //Cria botões para abrir outras janelas
        JButton cadastrarTransacao = new JButton("Cadastrar Transação");

        JButton gerarRelatorio = new JButton("Gerar Relatório");

        //Cria ações para os botões
        adicionarSaldo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
              
            }
        });

        //Painel para os botões
        JPanel painel2 = new JPanel();
        painel2.setLayout(new FlowLayout());

        //Adiciona os botões ao segundo painel
        painel2.add(cadastrarTransacao);
        painel2.add(gerarRelatorio);

        janela.add(painel);
        janela.add(painel2);

        janela.setVisible(true);
    }

    // Método para alterar o saldo
    public void setSaldo(String s) {
        saldoTexto.setText(s); // Atualiza o texto do campo de saldo
    }

}
