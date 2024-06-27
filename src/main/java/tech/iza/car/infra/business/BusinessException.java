package tech.iza.car.infra.business;

//Hierarquia de exceções

//É uma classe global, todas as nossas exceções vão extender dessa 'BusinessException' logo por ela ser uma exceção ela
//precisa extender de uma exceção também

public class BusinessException extends RuntimeException {

    private String id;
    private String suggestion;

    public BusinessException(String id, String message, String suggestion) {
        super(message);
        this.id = id;
        this.suggestion = suggestion;
    }

    public String getId() {
        return id;
    }

    public String getSuggestion() {
        return suggestion;
    }

    public int getHttpStatus() {
        return 409;
    }
}
