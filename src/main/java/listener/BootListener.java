package listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class BootListener implements ServletContextListener {
	
	// 톰캣서버 on
	public void contextInitialized(ServletContextEvent sce)  { 
    	// context(application)영역에 현재 접속자 수를 저장할 속성변수(attribute) 생성(초기화)
    	sce.getServletContext().setAttribute("currentCnt", 0);
    	System.out.println("[톰캣 실행] context currentCnt속성값 초기화");
    	
    	// 드라이브 로딩
    	try {
			Class.forName("org.mariadb.jdbc.Driver");
			System.out.println("BootListener 드라이브 로딩 성공");
		} catch (ClassNotFoundException e) {
			System.out.println("드라이브 로딩 실패");
			e.printStackTrace();
		}
    }
   
	// 톰캣서버 off
	public void contextDestroyed(ServletContextEvent sce)  { 

    }	
}
