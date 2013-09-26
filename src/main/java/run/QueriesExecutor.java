package run;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import dao.ListingDAO;

public class QueriesExecutor {
	static Logger logger = LogManager.getLogger(QueriesExecutor.class);
	public static void main(String[] args){
		ApplicationContext context = new ClassPathXmlApplicationContext("Bean.xml");
		ListingDAO obj = (ListingDAO) context.getBean("listingDAO");
		
		long startTime = System.currentTimeMillis();
		obj.q1_countOrdersByCountry();
		long timeElapsed = System.currentTimeMillis() - startTime;
		
		logger.info("q1: " + timeElapsed + "ms");
		
		
		startTime = System.currentTimeMillis();
		obj.q2_averageOrderTimeByYear();
		timeElapsed = System.currentTimeMillis() - startTime;
		logger.info("q2: " + timeElapsed + "ms");
		
		

		startTime = System.currentTimeMillis();
		obj.q3_soldProductsBySupplier();
		timeElapsed = System.currentTimeMillis() - startTime;
		logger.info("q3: " + timeElapsed + "ms");

		startTime = System.currentTimeMillis();
		obj.q4_amountByDayOfWeek();
		timeElapsed = System.currentTimeMillis() - startTime;
		logger.info("q4: " + timeElapsed + "ms");

		startTime = System.currentTimeMillis();
		obj.q5_valueOfSoldProductsBySupplierByYear();
		timeElapsed = System.currentTimeMillis() - startTime;
		logger.info("q5: " + timeElapsed + "ms");

		startTime = System.currentTimeMillis();
		obj.q6_averageValueOfShippedProductsByShipperByYear();
		timeElapsed = System.currentTimeMillis() - startTime;
		logger.info("q6: " + timeElapsed + "ms");
	}
}
