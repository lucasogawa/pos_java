package com.ogawalucas.atividade04;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor extends Thread {
    
    private static Socket socket;
    
    public Servidor(Socket conexao) {
        this.socket = conexao;
    }

    @Override
    public void run() {
        
        try {
            ObjectInputStream entrada = new ObjectInputStream(socket.getInputStream());
            DataOutputStream saida  = new DataOutputStream(socket.getOutputStream());
            
            Pessoa pessoa = (Pessoa) entrada.readObject();
            System.out.println("Nome: " + pessoa.getNome() + " Idade: " + pessoa.getIdade());

            saida.writeUTF("Dados recebidos corretamente!");

            socket.close();
        } catch(Exception e) {
            System.out.println("Ocorreu na thread do servidor!");
        }
        
    }

    public static void main(String[] args) {
        try {
            ServerSocket server = new ServerSocket(50000);

            while(true) {
                Socket conexao = server.accept();
                Servidor servidor = new Servidor(conexao);
                servidor.start();
            }            
        } catch(Exception ex) {
            System.out.println("Ocorreu no servidor!");
        }
    }   
}

