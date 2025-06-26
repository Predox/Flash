package com.flash.editorimagem.model;

import java.util.Date;

public class imagens {

    private int id_imagem;
    private int usuario_id;
    private String nome;
    private Date data_upload;

    public int getId_imagem() { return id_imagem; }
    
    public int getUsuario_id() { return usuario_id; }
    public void setUsuario_id(int usuario_id) { this.usuario_id = usuario_id; }
    
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    
    public Date getData_upload() { return data_upload; }
    public void setData_upload(Date data_upload) { this.data_upload = data_upload; }
    
}
