package com.mapa.social.demo.model; // Ajuste o pacote conforme a estrutura do seu projeto

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Column;

// Anotação que marca esta classe como uma entidade JPA,
// ou seja, ela será mapeada para uma tabela no banco de dados.
@Entity
public class Categoria {

    // Define 'id' como a chave primária da tabela.
    @Id
    // Configura a estratégia de geração automática para o 'id'.
    // GenerationType.IDENTITY é ideal para MySQL, onde o banco gerencia o auto-incremento.
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id; // Usamos Integer (wrapper) para permitir valores nulos antes de ser salvo

    // Define que a coluna 'nome' não pode ser nula e tem um tamanho máximo de 100 caracteres.
    @Column(nullable = false, length = 100)
    private String nome;

    // Define que a coluna 'descricao' pode ser nula e tem um tamanho máximo de 255 caracteres.
    @Column(length = 255)
    private String descricao; // Pode ser String ou Text, dependendo do tamanho esperado

    // --- Construtor Padrão (necessário para JPA) ---
    public Categoria() {
    }

    // --- Construtor com Campos (útil para criar objetos) ---
    public Categoria(String nome, String descricao) {
        this.nome = nome;
        this.descricao = descricao;
    }

    // --- Getters e Setters para todos os atributos ---

    public Integer getId() {
        return id;
    }

    // O setter para o ID geralmente não é usado em entidades gerenciadas pelo banco (IDENTITY),
    // mas é mantido por convenção e para alguns cenários específicos.
    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    // --- Método toString (útil para depuração) ---
    @Override
    public String toString() {
        return "Categoria{" +
               "id=" + id +
               ", nome='" + nome + '\'' +
               ", descricao='" + descricao + '\'' +
               '}';
    }
}