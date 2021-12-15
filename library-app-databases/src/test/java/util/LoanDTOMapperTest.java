package util;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import com.myapps.library_app_shared.model.LoanDTO;
import com.myapps.libraryapp_db.model.Loan;
import com.myapps.libraryapp_db.util.LoanDTOMapper;

public class LoanDTOMapperTest {

	@Test
	public void testToDTO() {
		String username = "test_user";
		String bookIsbn = "999999";
		LocalDate howLong = LocalDate.now().plusDays(30);
		Loan loan = new Loan(username, bookIsbn, howLong);
		
		LoanDTOMapper dtoMapper = new LoanDTOMapper();
		LoanDTO loanDTO = dtoMapper.toDTO(loan);
		
		assert(loanDTO.getId() == loan.getId());
	}
	
	@Test
	public void testToEntity() {
		String username = "test_user";
		String bookIsbn = "999999";
		LocalDate howLong = LocalDate.now().plusDays(30);
		LoanDTO loanDTO = new LoanDTO(1L, username, bookIsbn, howLong);
		
		LoanDTOMapper dtoMapper = new LoanDTOMapper();
		Loan loan = dtoMapper.toEntity(loanDTO);
		
		assert(loanDTO.getId() == loan.getId());
	}
}
