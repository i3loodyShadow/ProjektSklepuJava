package com.sklep.towar;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.primefaces.model.FilterMeta;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortMeta;

import com.sklep.entities.Towar;

public class LazyTowarDataModel extends LazyDataModel<Towar>{
	
	private List<Towar> datasource;

    public LazyTowarDataModel(List<Towar> datasource) {
        this.datasource = datasource;
    }
    
	@Override
    public List<Towar> load(int offset, int pageSize, Map<String, SortMeta> sortBy, Map<String, FilterMeta> filterBy) {
		//naprawiæ w jakiœ sposób ten kod (prawdopodobnie dostosowaæ go do moich potrzeb a nie korzystaæ z tego co by³o w primefaces)
        long rowCount = datasource.stream()
               .count();

	    // apply offset
        List<Towar> towar = datasource.stream()
	           .skip(offset)
	           .limit(pageSize)
	           .collect(Collectors.toList());

	   // rowCount
       setRowCount((int) rowCount);

       return towar;
	}
}
