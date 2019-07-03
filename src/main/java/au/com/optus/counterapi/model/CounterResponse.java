package au.com.optus.counterapi.model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * author : Chandan Singh
 * date : 01/07/19
 */

public class CounterResponse {

	private List<Map<String, Integer>> counts;

	public List<Map<String, Integer>> getCounts() {
		return counts;
	}

	public void setCounts(List<Map<String, Integer>> counts) {
		this.counts = counts;
	}

	@Override
	public String toString() {
		return "CounterResponse{" +
				"counts=" + counts +
				'}';
	}
}
