package br.com.alura.forum.config.validacao;

public class ErroFormDto {
	
	/*
	 * Classe para representar um erro de validação.
	 * Serão extraídos do JSON apenas esses 2 campos.  
	 * */

	private String campo;
	private String erro;

	public ErroFormDto(String campo, String erro) {

		this.campo = campo;
		this.erro = erro;
	}

	public String getCampo() {
		return campo;
	}

	public String getErro() {
		return erro;
	}

	
	
}
