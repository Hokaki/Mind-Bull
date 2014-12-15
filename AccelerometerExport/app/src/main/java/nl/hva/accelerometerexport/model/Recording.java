package nl.hva.accelerometerexport.model;

import java.util.ArrayList;
import java.util.List;

public class Recording {
	private List<Object[]> data;
	
	private String name;

	public List<Object[]> getData() {
		return data;
	}

	public void setData(List<Object[]> data) {
		this.data = data;
	}
	
	public void addData(Object[] dataF) {
		Object[] newDataF = { dataF[0], dataF[1], dataF[2], dataF[3] };
		if (data == null) {
			data = new ArrayList<Object[]>();
		}
		data.add(newDataF);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
