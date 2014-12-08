package nl.hva.accelerometerexport.model;

import java.util.ArrayList;
import java.util.List;

public class Recording {
	private List<Float[]> data;
	
	private String name;

	public List<Float[]> getData() {
		return data;
	}

	public void setData(List<Float[]> data) {
		this.data = data;
	}
	
	public void addData(float[] dataF) {
		Float[] newDataF = { dataF[0], dataF[1], dataF[2] };
		if (data == null) {
			data = new ArrayList<Float[]>();
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
