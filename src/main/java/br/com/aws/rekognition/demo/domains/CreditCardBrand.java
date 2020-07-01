package br.com.aws.rekognition.demo.domains;

public enum CreditCardBrand {

	//@formatter:off
	
	VISA("^4[0-9]{12}(?:[0-9]{3})?$"), 
	MASTERCARD("^(5[1-5][0-9]{14}|2(22[1-9][0-9]{12}|2[3-9][0-9]{13}|[3-6][0-9]{14}|7[0-1][0-9]{13}|720[0-9]{12}))$"),
	MAESTRO("^(5018|5020|5038|6304|6759|6761|6763)[0-9]{8,15}$"),
	AMEX("^3[47][0-9]{13}$");
	
	//@formatter:on

	private String pattern;

	CreditCardBrand(String pattern) {
		this.pattern = pattern;

	}

	public String getPattern() {
		return this.pattern;
	}

}
