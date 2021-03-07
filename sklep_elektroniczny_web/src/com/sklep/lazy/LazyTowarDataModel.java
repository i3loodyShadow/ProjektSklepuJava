package com.sklep.lazy;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.faces.context.FacesContext;

import org.primefaces.model.FilterMeta;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortMeta;
import org.primefaces.model.filter.FilterConstraint;
import org.primefaces.util.LocaleUtils;

import com.sklep.entities.Towar;

public class LazyTowarDataModel extends LazyDataModel<Towar>{
	private static final long serialVersionUID = 1L;
	
	private List<Towar> datasource;
	
	public List<Towar> getDatasource() {
		return datasource;
	}

	public void setDatasource(List<Towar> datasource) {
		this.datasource = datasource;
	}

    public LazyTowarDataModel(List<Towar> datasource) {
        this.datasource = datasource;
    }
    
    @Override
    public Towar getRowData(String rowKey) {
        for (Towar towar : datasource) {
            if (towar.getIdtowar() == Integer.parseInt(rowKey)) {
                return towar;
            }
        }

        return null;
    }

    @Override
    public String getRowKey(Towar towar) {
        return String.valueOf(towar.getIdtowar());
    }
    
	@Override
    public List<Towar> load(int offset, int pageSize, Map<String, SortMeta> sortBy, Map<String, FilterMeta> filterBy) {
		List<Towar> towarList = new ArrayList<Towar>();
		int rowCount = 0;
		if(datasource.size()>0) {
			rowCount = datasource.size();
			
			
			towarList = (List<Towar>) datasource.stream()
					.skip(offset)
					.limit(pageSize)
					.collect(Collectors.toList());

			setRowCount((int) rowCount);
			
			return towarList;
		} else {
			towarList = null;
			return towarList;
		}
	}
	
/*	private boolean filter(FacesContext context, Collection<FilterMeta> filterBy, Object o) {
        boolean matching = true;

        for (FilterMeta filter : filterBy) {
            FilterConstraint constraint = filter.getConstraint();
            Object filterValue = filter.getFilterValue();

            try {
                Object columnValue = String.valueOf(o.getClass().getField(filter.getFilterField()).get(o));
                matching = constraint.isMatching(context, columnValue, filterValue, LocaleUtils.getCurrentLocale());
            } catch (ReflectiveOperationException e) {
                matching = false;
            }

            if (!matching) {
                break;
            }
        }

        return matching;
    }
*/
}
