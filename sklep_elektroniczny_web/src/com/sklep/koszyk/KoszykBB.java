package com.sklep.koszyk;

import javax.faces.context.Flash;
import javax.inject.Inject;

public class KoszykBB {
	
	int idKonto;
	
	@Inject
	Flash flash;
	
	public String dodajDoKoszyka() {
		
		idKonto = (int) flash.get("idKonto");
		
		if(idKonto != 0) {
			
		}
		
		String ddk = "cos";
		
		return ddk;
	}

}
