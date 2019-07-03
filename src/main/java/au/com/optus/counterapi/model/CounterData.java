package au.com.optus.counterapi.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.List;

/**
 * author : Chandan Singh
 * date : 01/07/19
 */

public class CounterData implements Serializable {

    private static final long serialVersionUID = 1L;

    @JsonProperty("searchText")
	private List<String> counterDataList;

	public List<String> getCounterDataList() {
		return counterDataList;
	}

	public void setCounterDataList(List<String> counterDataList) {
		this.counterDataList = counterDataList;
	}

}
