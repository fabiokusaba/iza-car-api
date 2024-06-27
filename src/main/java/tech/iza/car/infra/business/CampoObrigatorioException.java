package tech.iza.car.infra.business;

public class CampoObrigatorioException extends BusinessException {

    public CampoObrigatorioException() {
        super("1", "Campo obrigatório", "Preencha os dados devidamente");
    }
}
