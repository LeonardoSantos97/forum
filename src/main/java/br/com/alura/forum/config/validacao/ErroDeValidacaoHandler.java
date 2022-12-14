package br.com.alura.forum.config.validacao;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/*
 * Classe para interceptar e realizar o tratamento de exceções em validações de formulários
*/

@RestControllerAdvice
public class ErroDeValidacaoHandler {

	
	// O Spring possui essa classe MessageSource que auxilia a capturar mensagens de erro. 
	
	@Autowired
	private MessageSource messageSource;
	
	/*
	 * Se cair qualquer excepetion MethodArgumentNotValidException, o Spring irá direcionar  para o método abaixo.
	 * @ResponseStatus(code = HttpStatus.BAD_REQUEST) é usado para continuar devolvendo erro 400 mesmo tratando a exceção.  
	 * */
	
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public List<ErroFormDto> handle(MethodArgumentNotValidException exception) {
		List<ErroFormDto> dto = new ArrayList<>();
		
		List<FieldError> fieldErrors = exception.getBindingResult().getFieldErrors();
		fieldErrors.forEach(e -> {
			String mensagem = messageSource.getMessage(e, LocaleContextHolder.getLocale());
					ErroFormDto erro = new ErroFormDto(e.getField(), mensagem);
					dto.add(erro);
		});
		
		
		return dto;
		
	}
}
