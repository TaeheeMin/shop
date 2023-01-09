package listener;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import service.SiteCounterService;

@WebListener
public class CounterListener implements HttpSessionListener {
	private SiteCounterService siteCounterService;
	
	// 세션이 생성될 때
    public void sessionCreated(HttpSessionEvent se)  { 
    	// 현재 서버의 접속자 수 (서버속성을 이용)
    	int num = (int)(se.getSession().getServletContext().getAttribute("currentCnt"));
    	se.getSession().getServletContext().setAttribute("currentCnt", num+1);
    	System.out.println("세션생성 접속자수 count : "+(num+1));
    	
    	// 누적 or 날짜별 접속자 수 (db)
    	this.siteCounterService = new SiteCounterService();
    	int todayCount = siteCounterService.selectTodayCount();
    	if(todayCount == 0) {
    		// 첫 접속자일 경우
    		System.out.println("오늘의 첫 접속자입니다");
    		siteCounterService.insertCount();
    	} else {
    		System.out.println("n번째 접속자십니다");
    		siteCounterService.updateCount();
    	}
    }

    // 세션이 소멸될 때
    public void sessionDestroyed(HttpSessionEvent se)  { 

    }
	
}
