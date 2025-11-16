package com.mapa.social.demo.dto;

public class ServicoMapaDTO {
    private Integer id;
    private String nome;
    private String tipo;
    private Double latitude;
    private Double longitude;
    private String enderecoResumo;

    public ServicoMapaDTO() {}

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }
    public Double getLatitude() { return latitude; }
    public void setLatitude(Double latitude) { this.latitude = latitude; }
    public Double getLongitude() { return longitude; }
    public void setLongitude(Double longitude) { this.longitude = longitude; }
    public String getEnderecoResumo() { return enderecoResumo; }
    public void setEnderecoResumo(String enderecoResumo) { this.enderecoResumo = enderecoResumo; }
}
