package tech.iza.car.infra;

//Nós precisamos trazer ou apresentar ou disponibilizar respostas tanto de sucesso quanto de possíveis falhas na nossa
//aplicação e muitas das vezes é subestimado a necessidade da gente criar uma padronização de resposta diante das nossas
//APIs

//Na nossa camada de service tudo o que for erro dentro da aplicação que não seja considerado um erro da aplicação no
//sentido de processos a gente considera como exceções de negócio e a gente chama de business exception

//Uma exceção de negócio é qualquer exceção que foi previamente mapeada porque imagináva-se que algo de convencional
//poderia acontecer, por exemplo tentar realizar uma requisição com ausência do nome

//Quando estamos falando de business exception algumas exceções elas são disparadas/interceptadas pelo spring e o spring
//vai retornar como uma resposta da nossa requisição

//Essa classe 'GlobalExceptionHandler' vai ter configurações globais de tratamento de exceções

import org.springframework.cglib.proxy.UndeclaredThrowableException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import tech.iza.car.infra.business.BusinessException;
import tech.iza.car.infra.http.Response;
import tech.iza.car.infra.http.ResponseFactory;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    private ResponseEntity<Object> handleGeneral(Exception e, WebRequest request) {
        String message = "";
        if (e.getClass().isAssignableFrom(UndeclaredThrowableException.class)) {
            UndeclaredThrowableException exception = (UndeclaredThrowableException) e;
            Class<? extends Throwable> exceptionClass = exception.getUndeclaredThrowable().getClass();
            return handleBusinessException((BusinessException) exception.getUndeclaredThrowable(), request);
        } else {
            BusinessMessage be = BusinessMessage.E501;
            Response error = ResponseFactory.error(Integer.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()), be.getMessage().concat(message), be.getSuggestion());
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            return handleExceptionInternal(e, error, headers, HttpStatus.INTERNAL_SERVER_ERROR, request);
        }

    }
    @ExceptionHandler({BusinessException.class})
    private ResponseEntity<Object> handleBusinessException(BusinessException be, WebRequest request) {
        Response error = ResponseFactory.error(be.getId(), be.getMessage(), be.getSuggestion());
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        ResponseEntity response = handleExceptionInternal(be, error, headers, HttpStatus.resolve(be.getHttpStatus()), request);
        return response;
    }
}

