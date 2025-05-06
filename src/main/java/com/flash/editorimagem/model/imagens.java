package com.flash.editorimagem.model;

import java.util.Date;

public class imagens {

    private int id_imagem;
    private int usuario_id;
    private String caminho_arquivo;
    private Date data_upload;

    public int getId_imagem() { return id_imagem; }
    
    public int getUsuario_id() { return usuario_id; }
    public void setUsuario_id(int usuario_id) { this.usuario_id = usuario_id; }
    
    public String getCaminho_arquivo() { return caminho_arquivo; }
    public void setCaminho_arquivo(String caminho_arquivo) { this.caminho_arquivo = caminho_arquivo; }
    
    public Date getData_upload() { return data_upload; }
    public void setData_upload(Date data_upload) { this.data_upload = data_upload; }
    
}
