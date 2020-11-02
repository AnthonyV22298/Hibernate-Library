package com.ss.lms.Service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ss.lms.Entity.Copies;
import com.ss.lms.Repo.CopiesRepo;

@RestController
public class Librarian {
	
	@Autowired
	CopiesRepo crepo;
	
	@RequestMapping(value = "/getCopies", method = RequestMethod.GET, produces = "application/json")
	public List<Copies> getGenres(@RequestParam int bid, @RequestParam int brid) {
		List<Copies> copies = new ArrayList<>();
		if (bid != 0 && brid != 0) {
			copies = crepo.readCopiesByIds(bid, brid);
		} else {
			copies = crepo.findAll();
		}
		return copies;
	}
	
	@RequestMapping(value = "/addBookCopies", method = RequestMethod.PUT)
	public void addBookCopies(@RequestParam int bid, @RequestParam int brid, @RequestParam int copies) {
		Copies c = crepo.readCopiesByIds(bid, brid).get(0);
		
		c.addNoOfCopies(copies);
		crepo.save(c);
	}
	

}
