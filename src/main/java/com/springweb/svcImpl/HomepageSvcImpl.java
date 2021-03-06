package com.springweb.svcImpl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.springweb.svc.HomepageSvc;
import com.springweb.utils.HomePageJsonGenerator;

@Service
public class HomepageSvcImpl implements HomepageSvc {

	
	@Override
	public Map<String, String> getHomepageCount() {
		//String count =  dao.getHomepageCount();
		Map<String, String> countMap = new HashMap<String, String>();
		
		HomePageJsonGenerator chart = new HomePageJsonGenerator();
        String barChartJson = chart.generateJSONforBarChart();
        String pieChartJson = chart.generateJSONforPieChart();
        
        countMap.put("barChartJson", barChartJson);
        countMap.put("pieChartJson", pieChartJson);
		
		return countMap;
	}
	

}
