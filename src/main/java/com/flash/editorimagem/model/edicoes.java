package com.flash.editorimagem.model;

import java.util.Date;

public class edicoes {
    private int id_edicao;
    private int imagem_id;
    private int preset_id;
    private int saturacao;
    private int exposicao;
    private int contraste;
    private int realce;
    private int sombras;
    private int brancos;
    private int pretos;
    private int textura;
    private Date data_edicao;

    public int getId_edicao() { return id_edicao; }
    
    public int getImagem_id() { return imagem_id; }
    public void setImagem_id(int imagem_id) { this.imagem_id = imagem_id; }
    
    public int getPreset_id() { return preset_id; }
    public void setPreset_id(int preset_id) { this.preset_id = preset_id; }
    
    public int getSaturacao() { return saturacao; }
    public void setSaturacao(int saturacao) { this.saturacao = saturacao; }
    
    public int getExposicao() { return exposicao; }
    public void setExposicao(int exposicao) { this.exposicao = exposicao; }
    
    public int getContraste() { return contraste; }
    public void setContraste(int contraste) { this.contraste = contraste; }
    
    public int getRealce() { return realce; }
    public void setRealce(int realce) { this.realce = realce; }
    
    public int getSombras() { return sombras; }
    public void setSombras(int sombras) { this.sombras = sombras; }
    
    public int getBrancos() { return brancos; }
    public void setBrancos(int brancos) { this.brancos = brancos; }
    
    public int getPretos() { return pretos; }
    public void setPretos(int pretos) { this.pretos = pretos; }
    
    public int getTextura() { return textura; }
    public void setTextura(int textura) { this.textura = textura; }
    
    public Date getData_edicao() { return data_edicao; }
    public void setData_edicao(Date data_edicao) { this.data_edicao = data_edicao; }
    
}
