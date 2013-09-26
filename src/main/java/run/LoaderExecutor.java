package run;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import tuning.Loader.Loader;

public class LoaderExecutor {
	static Logger logger = LogManager.getLogger(LoaderExecutor.class);

	public static void main(String[] args){
		ApplicationContext context = new ClassPathXmlApplicationContext("Bean.xml");
		logger.error("Starting loading data------------------------------------------------------------------------------");
		Loader obj = (Loader) context.getBean("loader");
		obj.loadData();
	}
}
