package tech.iza.car.infra.business;

public class RegistroNaoLocalizadoException extends BusinessException {

    public RegistroNaoLocalizadoException() {
        super("2", "Registro não localizado", "Insira um registro previamente");
    }
}
