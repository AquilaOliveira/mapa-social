
package com.mapa.social.demo.model; // Ajuste o pacote conforme a estrutura do seu projeto

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Column;

@Entity // Marca esta classe como uma entidade JPA
public class Endereco {

    @Id // Define 'id' como a chave primária
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-incremento no BD
    private Integer id; // Usamos Integer (wrapper)

    @Column(nullable = false, length = 100) // Rua não pode ser nula, até 100 caracteres
    private String rua;

    @Column(nullable = false) // Número não pode ser nulo
    private Integer numero; // Usamos Integer (wrapper)

    @Column(nullable = false, length = 100) // Bairro não pode ser nulo, até 100 caracteres
    private String bairro;

    @Column(nullable = false, length = 100) // Cidade não pode ser nula, até 100 caracteres
    private String cidade;

    // char uf -> No Java, um String de 2 caracteres é mais comum para UF
    @Column(nullable = false, length = 2) // UF não pode ser nula, 2 caracteres
    private String uf;

    @Column(nullable = false, length = 9) // CEP não pode ser nulo, 9 caracteres (formato "XXXXX-XXX")
    private String cep;

    @Column(length = 255) // Complemento pode ser nulo, até 255 caracteres
    private String complemento;

    // --- Construtor Padrão (necessário para JPA) ---
    public Endereco() {
    }

    // --- Construtor com Campos (útil para criar objetos) ---
    public Endereco(String rua, Integer numero, String bairro, String cidade, String uf, String cep, String complemento) {
        this.rua = rua;
        this.numero = numero;
        this.bairro = bairro;
        this.cidade = cidade;
        this.uf = uf;
        this.cep = cep;
        this.complemento = complemento;
    }

    // --- Getters e Setters para todos os atributos ---

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    // --- Método toString (útil para depuração) ---
    @Override
    public String toString() {
        return "Endereco{" +
               "id=" + id +
               ", rua='" + rua + '\'' +
               ", numero=" + numero +
               ", bairro='" + bairro + '\'' +
               ", cidade='" + cidade + '\'' +
               ", uf='" + uf + '\'' +
               ", cep='" + cep + '\'' +
               ", complemento='" + complemento + '\'' +
               '}';
    }
}