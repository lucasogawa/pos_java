package com.ogawalucas.atividade03;

import java.io.DataOutputStream;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {
    
    private static Socket socket;
    private static ServerSocket server;
    private static ObjectInputStream entrada;
    private static DataOutputStream saida;
    
    public static void main(String[] args) {
   
        try {
            server = new ServerSocket(50000);
            socket = server.accept();            
            entrada = new ObjectInputStream(socket.getInputStream());
            saida = new DataOutputStream(socket.getOutputStream());
            
            Pessoa pessoa = (Pessoa) entrada.readObject();
            System.out.println("Nome: " + pessoa.getNome() + " Idade: " + pessoa.getIdade());

            saida.writeUTF("Dados recebidos corretamente!");

            socket.close();
        } catch(Exception ex) {
            System.out.println("Ocorreu no servidor!");
        }
    }   
}
