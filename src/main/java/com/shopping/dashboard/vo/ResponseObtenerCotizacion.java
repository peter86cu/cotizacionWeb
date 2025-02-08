package com.shopping.dashboard.vo;


public class ResponseObtenerCotizacion {
	
	private int code;
	private boolean status;
	private String resultado;
	private ErrorState error;
	
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	
	public String getResultado() {
		return resultado;
	}
	public void setResultado(String resultado) {
		this.resultado = resultado;
	}
	public ErrorState getError() {
		return error;
	}
	public void setError(ErrorState error) {
		this.error = error;
	}
	

}
