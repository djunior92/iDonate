package br.com.idonate.iDonate.exceptionhandler;

import br.com.idonate.iDonate.service.exception.*;
import lombok.Getter;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@ControllerAdvice
public class IDonateExceptionHandler extends ResponseEntityExceptionHandler {

    @Autowired
    private MessageSource messageSource;

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
                                                                  HttpHeaders headers, HttpStatus status,
                                                                  WebRequest request) {
        String userMessage = messageSource.getMessage("mensagem.invalida", null, LocaleContextHolder.getLocale());
        String developerMessage = ex.getCause().toString();
        List<Erro> erros = Collections.singletonList(new Erro(userMessage, developerMessage));
        return handleExceptionInternal(ex, erros, headers, HttpStatus.BAD_REQUEST, request);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers, HttpStatus status, WebRequest request) {
        List<Erro> erros = createErrorList(ex.getBindingResult());
        return handleExceptionInternal(ex, erros, headers, HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler({ EmptyResultDataAccessException.class })
    public ResponseEntity<Object> handleEmptyResultDataAccessException(EmptyResultDataAccessException ex, WebRequest request) {
        String mensagemUsuario = messageSource.getMessage("recurso.nao-encontrado", null, LocaleContextHolder.getLocale());
        String mensagemDesenvolvedor = ex.toString();
        List<Erro> erros = Collections.singletonList(new Erro(mensagemUsuario, mensagemDesenvolvedor));
        return handleExceptionInternal(ex, erros, new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }

    @ExceptionHandler({ DataIntegrityViolationException.class } )
    public ResponseEntity<Object> handleDataIntegrityViolationException(DataIntegrityViolationException ex, WebRequest request) {
        String mensagemUsuario = messageSource.getMessage("recurso.operacao-nao-permitida", null, LocaleContextHolder.getLocale());
        String mensagemDesenvolvedor = ExceptionUtils.getRootCauseMessage(ex);
        List<Erro> erros = Collections.singletonList(new Erro(mensagemUsuario, mensagemDesenvolvedor));
        return handleExceptionInternal(ex, erros, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler({ CampaignAndBenefitedNotInformedException.class } )
    public ResponseEntity<Object> handleCampaignAndBenefitedNotInformedException(CampaignAndBenefitedNotInformedException ex, WebRequest request) {
        String mensagemUsuario = messageSource.getMessage("campanha.beneficiado-nao-encontrado", null, LocaleContextHolder.getLocale());
        String mensagemDesenvolvedor = ExceptionUtils.getRootCauseMessage(ex);
        List<Erro> erros = Collections.singletonList(new Erro(mensagemUsuario, mensagemDesenvolvedor));
        return handleExceptionInternal(ex, erros, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler({ CreditCardNotRegisteredException.class } )
    public ResponseEntity<Object> handleCreditCardNotRegisteredException(CreditCardNotRegisteredException ex, WebRequest request) {
        String mensagemUsuario = messageSource.getMessage("cartao.nao-registrado", null, LocaleContextHolder.getLocale());
        String mensagemDesenvolvedor = ExceptionUtils.getRootCauseMessage(ex);
        List<Erro> erros = Collections.singletonList(new Erro(mensagemUsuario, mensagemDesenvolvedor));
        return handleExceptionInternal(ex, erros, new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }

    @ExceptionHandler({ DonationNotRegisteredException.class } )
    public ResponseEntity<Object> handleDonationNotRegisteredException(DonationNotRegisteredException ex, WebRequest request) {
        String mensagemUsuario = messageSource.getMessage("doacao.nao-registrado", null, LocaleContextHolder.getLocale());
        String mensagemDesenvolvedor = ExceptionUtils.getRootCauseMessage(ex);
        List<Erro> erros = Collections.singletonList(new Erro(mensagemUsuario, mensagemDesenvolvedor));
        return handleExceptionInternal(ex, erros, new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }

    @ExceptionHandler({ InvalidCodValidationException.class } )
    public ResponseEntity<Object> handleInvalidCodValidationException(InvalidCodValidationException ex, WebRequest request) {
        String mensagemUsuario = messageSource.getMessage("usuario.cod-valid-invalido", null, LocaleContextHolder.getLocale());
        String mensagemDesenvolvedor = ExceptionUtils.getRootCauseMessage(ex);
        List<Erro> erros = Collections.singletonList(new Erro(mensagemUsuario, mensagemDesenvolvedor));
        return handleExceptionInternal(ex, erros, new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }

    @ExceptionHandler({InvalidEmailException.class})
    public ResponseEntity<Object> handleInvalidEmailException(InvalidEmailException ex, WebRequest request) {
        String mensagemUsuario = messageSource.getMessage("email.invalido", null, LocaleContextHolder.getLocale());
        String mensagemDesenvolvedor = ex.toString();
        List<Erro> erros = Collections.singletonList(new Erro(mensagemUsuario, mensagemDesenvolvedor));
        return handleExceptionInternal(ex, erros, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler({InvalidLoginException.class})
    public ResponseEntity<Object> handleInvalidLoginException(InvalidLoginException ex, WebRequest request) {
        String mensagemUsuario = messageSource.getMessage("usuario.login-invalido", null, LocaleContextHolder.getLocale());
        String mensagemDesenvolvedor = ex.toString();
        List<Erro> erros = Collections.singletonList(new Erro(mensagemUsuario, mensagemDesenvolvedor));
        return handleExceptionInternal(ex, erros, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler({InvalidTokenException.class})
    public ResponseEntity<Object> handleInvalidTokenException(InvalidTokenException ex, WebRequest request) {
        String mensagemUsuario = messageSource.getMessage("token.invalido", null, LocaleContextHolder.getLocale());
        String mensagemDesenvolvedor = ex.toString();
        List<Erro> erros = Collections.singletonList(new Erro(mensagemUsuario, mensagemDesenvolvedor));
        return handleExceptionInternal(ex, erros, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler({ InvalidValueException.class })
    public ResponseEntity<Object> handleRegisterNotFoundException(InvalidValueException ex, WebRequest request) {
        String mensagemUsuario = messageSource.getMessage("cotacao.invalda", null, LocaleContextHolder.getLocale());
        String mensagemDesenvolvedor = ex.toString();
        List<Erro> erros = Collections.singletonList(new Erro(mensagemUsuario, mensagemDesenvolvedor));
        return handleExceptionInternal(ex, erros, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler({ LoginAlreadyValidatedException.class })
    public ResponseEntity<Object> handleLoginAlreadyValidatedException(LoginAlreadyValidatedException ex, WebRequest request) {
        String mensagemUsuario = messageSource.getMessage("usuario.login-ja-validado", null, LocaleContextHolder.getLocale());
        String mensagemDesenvolvedor = ex.toString();
        List<Erro> erros = Collections.singletonList(new Erro(mensagemUsuario, mensagemDesenvolvedor));
        return handleExceptionInternal(ex, erros, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler({LoginUnavailableException.class})
    public ResponseEntity<Object> handleLoginUnavailableException(LoginUnavailableException ex, WebRequest request) {
        String mensagemUsuario = messageSource.getMessage("usuario.existente", null, LocaleContextHolder.getLocale());
        String mensagemDesenvolvedor = ex.toString();
        List<Erro> erros = Collections.singletonList(new Erro(mensagemUsuario, mensagemDesenvolvedor));
        return handleExceptionInternal(ex, erros, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler({NewAndOldPasswordAlikeException.class})
    public ResponseEntity<Object> handleNewAndOldPasswordAlikeException(NewAndOldPasswordAlikeException ex, WebRequest request) {
        String mensagemUsuario = messageSource.getMessage("usuario.senhas-iguais", null, LocaleContextHolder.getLocale());
        String mensagemDesenvolvedor = ex.toString();
        List<Erro> erros = Collections.singletonList(new Erro(mensagemUsuario, mensagemDesenvolvedor));
        return handleExceptionInternal(ex, erros, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler({NumberOfPointsInvalidException.class})
    public ResponseEntity<Object> handleNumberOfPointsInvalidException(NumberOfPointsInvalidException ex, WebRequest request) {
        String mensagemUsuario = messageSource.getMessage("pontos.numero-invalido", null, LocaleContextHolder.getLocale());
        String mensagemDesenvolvedor = ex.toString();
        List<Erro> erros = Collections.singletonList(new Erro(mensagemUsuario, mensagemDesenvolvedor));
        return handleExceptionInternal(ex, erros, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler({PasswordOldInvalidException.class})
    public ResponseEntity<Object> handlePasswordOldInvalidException(PasswordOldInvalidException ex, WebRequest request) {
        String mensagemUsuario = messageSource.getMessage("usuario.senha-antiga-invalida", null, LocaleContextHolder.getLocale());
        String mensagemDesenvolvedor = ex.toString();
        List<Erro> erros = Collections.singletonList(new Erro(mensagemUsuario, mensagemDesenvolvedor));
        return handleExceptionInternal(ex, erros, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler({ ProfileNotRegisteredException.class } )
    public ResponseEntity<Object> handleProfileNotRegisteredException(ProfileNotRegisteredException ex, WebRequest request) {
        String mensagemUsuario = messageSource.getMessage("perfil.nao-registrado", null, LocaleContextHolder.getLocale());
        String mensagemDesenvolvedor = ExceptionUtils.getRootCauseMessage(ex);
        List<Erro> erros = Collections.singletonList(new Erro(mensagemUsuario, mensagemDesenvolvedor));
        return handleExceptionInternal(ex, erros, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler({ PaymentRefusedException.class } )
    public ResponseEntity<Object> handlePaymentRefusedException(PaymentRefusedException ex, WebRequest request) {
        String mensagemUsuario = messageSource.getMessage("pagamento.nao-autorizado", null, LocaleContextHolder.getLocale());
        String mensagemDesenvolvedor = ExceptionUtils.getRootCauseMessage(ex);
        List<Erro> erros = Collections.singletonList(new Erro(mensagemUsuario, mensagemDesenvolvedor));
        return handleExceptionInternal(ex, erros, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler({ ProfileOrCampaignNotInformedException.class } )
    public ResponseEntity<Object> handleProfileOrCampaignNotInformedException(ProfileOrCampaignNotInformedException ex, WebRequest request) {
        String mensagemUsuario = messageSource.getMessage("comentario.comentado-nao-informado", null, LocaleContextHolder.getLocale());
        String mensagemDesenvolvedor = ExceptionUtils.getRootCauseMessage(ex);
        List<Erro> erros = Collections.singletonList(new Erro(mensagemUsuario, mensagemDesenvolvedor));
        return handleExceptionInternal(ex, erros, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler({ QuotationNotRegisteredException.class } )
    public ResponseEntity<Object> handleQuotationNotRegisteredException(QuotationNotRegisteredException ex, WebRequest request) {
        String mensagemUsuario = messageSource.getMessage("cotacao.nao-registrado", null, LocaleContextHolder.getLocale());
        String mensagemDesenvolvedor = ExceptionUtils.getRootCauseMessage(ex);
        List<Erro> erros = Collections.singletonList(new Erro(mensagemUsuario, mensagemDesenvolvedor));
        return handleExceptionInternal(ex, erros, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler({ RechargeNotRegisteredException.class } )
    public ResponseEntity<Object> handleRechargeNotRegisteredException(RechargeNotRegisteredException ex, WebRequest request) {
        String mensagemUsuario = messageSource.getMessage("recarga.nao-registrado", null, LocaleContextHolder.getLocale());
        String mensagemDesenvolvedor = ExceptionUtils.getRootCauseMessage(ex);
        List<Erro> erros = Collections.singletonList(new Erro(mensagemUsuario, mensagemDesenvolvedor));
        return handleExceptionInternal(ex, erros, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler({ RedeemNotChangeDeposited.class } )
    public ResponseEntity<Object> handleRedeemNotChangeDeposited(RedeemNotChangeDeposited ex, WebRequest request) {
        String mensagemUsuario = messageSource.getMessage("resgate.nao-registrado", null, LocaleContextHolder.getLocale());
        String mensagemDesenvolvedor = ExceptionUtils.getRootCauseMessage(ex);
        List<Erro> erros = Collections.singletonList(new Erro(mensagemUsuario, mensagemDesenvolvedor));
        return handleExceptionInternal(ex, erros, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler({ RedeemNotRegisteredException.class } )
    public ResponseEntity<Object> handleRedeemNotRegisteredException(RedeemNotRegisteredException ex, WebRequest request) {
        String mensagemUsuario = messageSource.getMessage("resgate.nao-depositado", null, LocaleContextHolder.getLocale());
        String mensagemDesenvolvedor = ExceptionUtils.getRootCauseMessage(ex);
        List<Erro> erros = Collections.singletonList(new Erro(mensagemUsuario, mensagemDesenvolvedor));
        return handleExceptionInternal(ex, erros, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler({ RegisterNotFoundException.class })
    public ResponseEntity<Object> handleRegisterNotFoundException(RegisterNotFoundException ex, WebRequest request) {
        String mensagemUsuario = messageSource.getMessage("recurso.nao-encontrado", null, LocaleContextHolder.getLocale());
        String mensagemDesenvolvedor = ex.toString();
        List<Erro> erros = Collections.singletonList(new Erro(mensagemUsuario, mensagemDesenvolvedor));
        return handleExceptionInternal(ex, erros, new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }

    private List<Erro> createErrorList(BindingResult bindingResult) {
        List<Erro> erros = new ArrayList<>();

        for (FieldError fieldError : bindingResult.getFieldErrors()) {
            String userMessage = messageSource.getMessage(fieldError, LocaleContextHolder.getLocale());
            String developerMessage = fieldError.toString();
            erros.add(new Erro(userMessage, developerMessage));
        }

        return erros;
    }

    public static class Erro {

        public Erro(String userMessage, String developerMessage) {
            this.userMessage = userMessage;
            this.developerMessage = developerMessage;
        }

        @Getter private String userMessage;
        @Getter private String developerMessage;

    }

}
