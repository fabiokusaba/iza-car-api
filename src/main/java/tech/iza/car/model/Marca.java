package tech.iza.car.model;

//Java Beans
//Lombok
public class Marca {

    //Nós detectamos que atributos de classes de modelo ou de domínio precisam ser privados
    private Integer id;
    private String nome;

    //Para que os nossos atributos sejam acessados pela nossa aplicação precisamos ter os métodos acessores e métodos de
    //definição
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
