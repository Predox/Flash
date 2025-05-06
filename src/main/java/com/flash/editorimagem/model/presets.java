package com.flash.editorimagem.model;

public class presets {
    private int id_preset;
    private String nome;
    private int usuario_id;
    private int saturacao;
    private int exposicao;
    private int contraste;
    private int realce;
    private int sombras;
    private int brancos;
    private int pretos;
    private int textura;

    public int getId_preset() { return id_preset; }
    public void setPreset_id(int id_preset) { this.id_preset = id_preset; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public int getUsuario_id() { return usuario_id; }
    public void setUsuario_id(int usuario_id) { this.usuario_id = usuario_id; }

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

}
