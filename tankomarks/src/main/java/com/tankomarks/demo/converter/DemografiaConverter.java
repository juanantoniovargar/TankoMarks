/* 
package com.tankomarks.demo.converter;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import com.tankomarks.demo.model.Demografia;
import com.tankomarks.demo.repository.DemografiaRepository;

public class DemografiaConverter implements Converter<String, Demografia> {
	
	@Autowired
	private DemografiaRepository demografiaRepo;

	public DemografiaConverter(@Autowired DemografiaRepository demografiaRepo) {
		super();
		this.demografiaRepo = demografiaRepo;
	}

	public DemografiaConverter() {
		super();
	}

	@Override
    public Demografia convert(String source) {
    	
    	Demografia demografia = demografiaRepo.convierteDemografia(source);

    	return demografia;
    	
    }

}
*/