import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

public class Log4j2Ex {
	private static final Logger logger = LogManager.getLogger(Log4j2Ex.class.getName());

	
	public static void main(String[] args) {
		logger.debug("debug log!!!");
		
		int x = 100;
		if (logger.isInfoEnabled()) {
			logger.info("main(String[]) - int x={}", x);
		}
		
		method();
		method(11,22);
	}
	
	
	public static void method() {
		if (logger.isDebugEnabled()) {
			logger.debug("method() - start");
		}
		
		
		
		if (logger.isDebugEnabled()) {
			logger.debug("method() - end"); 
		}
	}
	public static int method(int x, int y) {
		
		if (logger.isDebugEnabled()) {
			logger.debug("method(int, int) - start{},{}",x,y); 
		}
		
		if (logger.isDebugEnabled()) {
			logger.debug("method(int, int) - end {}", 0); 
		}
		return 0;
	}
}
