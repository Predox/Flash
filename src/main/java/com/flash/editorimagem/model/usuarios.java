package com.flash.editorimagem.model;

public class usuarios {
    private int id_usuario;
    private String nome;
    private String email;
    private String senha;

    public int getId_usuario() { return id_usuario; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getSenha() { return senha; }
    public void setSenha(String senha) { this.senha = senha; }
}
