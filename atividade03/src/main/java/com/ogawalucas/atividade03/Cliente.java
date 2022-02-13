package com.ogawalucas.atividade03;

import java.io.DataInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Cliente extends javax.swing.JFrame {

    private javax.swing.JButton jButtonEnviar;
    private javax.swing.JLabel jLabelIdade;
    private javax.swing.JLabel jLabelNome;
    private javax.swing.JLabel jLabelRetornoServidor;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextAreaRetornoServidor;
    private javax.swing.JTextField jTextFieldIdade;
    private javax.swing.JTextField jTextFieldNome;

    private static Socket socket;
    private static ObjectOutputStream saida;
    private static DataInputStream entrada;

    public Cliente() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {
        jLabelNome = new javax.swing.JLabel();
        jTextFieldNome = new javax.swing.JTextField();
        jLabelIdade = new javax.swing.JLabel();
        jTextFieldIdade = new javax.swing.JTextField();
        jLabelRetornoServidor = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextAreaRetornoServidor = new javax.swing.JTextArea();
        jButtonEnviar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabelNome.setText("Nome");

        jLabelIdade.setText("Idade");

        jLabelRetornoServidor.setText("Retorno do Servidor");

        jTextAreaRetornoServidor.setColumns(20);
        jTextAreaRetornoServidor.setRows(5);
        jTextAreaRetornoServidor.setText("Recebeu do servidor:");
        jScrollPane1.setViewportView(jTextAreaRetornoServidor);

        jButtonEnviar.setText("Enviar");
        jButtonEnviar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonEnviarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jTextFieldNome))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addComponent(jLabelNome, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jTextFieldIdade))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap(15, Short.MAX_VALUE)
                        .addComponent(jLabelIdade, javax.swing.GroupLayout.PREFERRED_SIZE, 379, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButtonEnviar)))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabelRetornoServidor, javax.swing.GroupLayout.PREFERRED_SIZE, 386, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jLabelNome)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextFieldNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(42, 42, 42)
                .addComponent(jLabelIdade)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextFieldIdade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 35, Short.MAX_VALUE)
                .addComponent(jLabelRetornoServidor)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addComponent(jButtonEnviar))
        );

        pack();
    }

    private void jButtonEnviarActionPerformed(java.awt.event.ActionEvent evt) {
        enviar();
    }

    private void enviar() {
        try {
            socket = new Socket("127.0.0.1", 50000);
            saida = new ObjectOutputStream(socket.getOutputStream());
            entrada = new DataInputStream(socket.getInputStream());
            
            saida.writeObject(criarPessoa());
            mostrarRetornoServidor(entrada.readUTF());

            socket.close();
        } catch(Exception ex) {
            System.out.println("Ocorreu um erro durante o envio dos dados para "
                + "o servidor.");
        }
    }

    private Pessoa criarPessoa() {
        return new Pessoa(jTextFieldNome.getText(), Integer.parseInt(jTextFieldIdade.getText()));
    }

    private void mostrarRetornoServidor(String retornoServidor) {
        jTextAreaRetornoServidor.setText(jTextAreaRetornoServidor.getText() 
            + "\n" + retornoServidor);
    }

    public static void main(String args[]) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Cliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Cliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Cliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Cliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Cliente().setVisible(true);
            }
        });
    }
}
