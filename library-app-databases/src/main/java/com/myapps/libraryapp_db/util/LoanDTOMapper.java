package com.myapps.libraryapp_db.util;

import java.util.ArrayList;
import java.util.List;

import com.myapps.library_app_shared.model.LoanDTO;
import com.myapps.libraryapp_db.model.Loan;

public class LoanDTOMapper {
	public LoanDTO toDTO(Loan loan) {
		return new LoanDTO(	loan.getUsername(),
							loan.getBookId(),
							loan.getDueDate());
	}
	
	public List<LoanDTO> toDTO(List<Loan> allLoans){
		
		List<LoanDTO> allDTOs= new ArrayList<LoanDTO>();
		for(Loan loan : allLoans) {
			allDTOs.add(toDTO(loan));
		}
		
		return allDTOs;
	}
	
	public Loan toEntity(LoanDTO loanDTO) {
		return new Loan(loanDTO.getUsername(),
						loanDTO.getBookId(),
						loanDTO.getDueDate());
	}
}
