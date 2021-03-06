package com.sklep.lazy;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.primefaces.model.FilterMeta;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortMeta;

import com.sklep.entities.TowarZamowienia;

public class LazyTowarZamowieniaDataModel extends LazyDataModel<TowarZamowienia>{
	private static final long serialVersionUID = 1L;
	
	private List<TowarZamowienia> datasource;
		
	public List<TowarZamowienia> getDatasource() {
		return datasource;
	}

    public LazyTowarZamowieniaDataModel(List<TowarZamowienia> datasource) {
        this.datasource = datasource;
    }
	    
	@Override
    public List<TowarZamowienia> load(int offset, int pageSize, Map<String, SortMeta> sortBy, Map<String, FilterMeta> filterBy) {
			
		int rowCount=0;
		for(int i=1;i<=datasource.size();i++) {
			if(i==datasource.size()) {
					rowCount = i;
			}
		}
		
		List<TowarZamowienia> towarZamowieniaList = new ArrayList<TowarZamowienia>();
		towarZamowieniaList = (List<TowarZamowienia>) datasource.stream()
				.skip(offset)
				.limit(pageSize)
				.collect(Collectors.toList());
		
		setRowCount((int) rowCount);
			
		return towarZamowieniaList;
	}
	
}
