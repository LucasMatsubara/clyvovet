package br.com.fiap.clyvopaws.exception;

import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ErroPadrao> tratarErro404(EntityNotFoundException e, HttpServletRequest request) {
        ErroPadrao erro = new ErroPadrao(
                LocalDateTime.now(),
                HttpStatus.NOT_FOUND.value(),
                "Não Encontrado",
                e.getMessage(),
                request.getRequestURI()
        );
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erro);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErroPadrao> tratarRegraDeNegocio(IllegalArgumentException e, HttpServletRequest request) {
        ErroPadrao erro = new ErroPadrao(
                LocalDateTime.now(),
                HttpStatus.BAD_REQUEST.value(),
                "Regra de Negócio/Requisição Inválida",
                e.getMessage(),
                request.getRequestURI()
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erro);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErroValidacao> tratarErroValidacao(MethodArgumentNotValidException e, HttpServletRequest request) {
        List<ErroValidacao.CampoInvalido> campos = e.getBindingResult().getFieldErrors().stream()
                .map(erro -> new ErroValidacao.CampoInvalido(erro.getField(), erro.getDefaultMessage()))
                .toList();

        ErroValidacao erro = new ErroValidacao(
                LocalDateTime.now(),
                HttpStatus.BAD_REQUEST.value(),
                "Erro de Validação de Dados",
                request.getRequestURI(),
                campos
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erro);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErroPadrao> tratarErro500(Exception e, HttpServletRequest request) {
        ErroPadrao erro = new ErroPadrao(
                LocalDateTime.now(),
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                "Erro Interno no Servidor",
                "Ocorreu um erro inesperado. Contate o suporte.",
                request.getRequestURI()
        );
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(erro);
    }
}