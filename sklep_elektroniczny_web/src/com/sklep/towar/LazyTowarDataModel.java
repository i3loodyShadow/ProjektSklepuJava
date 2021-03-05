package com.sklep.towar;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.primefaces.model.FilterMeta;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortMeta;

import com.sklep.entities.Towar;

public class LazyTowarDataModel extends LazyDataModel<Towar>{
	
	private List<Towar> datasource;
	
	public List<Towar> getDatasource() {
		return datasource;
	}

	public void setDatasource(List<Towar> datasourcee) {
		this.datasource = datasourcee;
	}

    public LazyTowarDataModel(List<Towar> datasource) {
        this.datasource = datasource;
    }
    
	@Override
    public List<Towar> load(int offset, int pageSize, Map<String, SortMeta> sortBy, Map<String, FilterMeta> filterBy) {
		int rowCount = 0;
		for(int i=0;i<datasource.size();i++) {
			if(i==datasource.size()-1) {
				rowCount = i;
			}
		}
		
		List<Towar> towarList = new ArrayList<Towar>();
		towarList = (List<Towar>) datasource.stream()
				.skip(offset)
				.limit(pageSize)
				.collect(Collectors.toList());
		
		setRowCount((int) rowCount);
		
		return towarList;
	}
}
