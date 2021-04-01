package com.example.demo.Filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AccessIpFilter implements Filter {
	
//	@Autowired
//	private AccessIpService service;
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		// 요청을 받을 때 전달받은 정보를 HttpServletRequest에 저장
		HttpServletRequest req = (HttpServletRequest) request;

		String ip = req.getRemoteAddr();
		log.info("request ip  : {}", ip);
//		List<AccessIp> ip2 = service.accessIp();
		if (ip.equals("0:0:0:0:0:0:0:1") || ip.equals("127.0.0.1")) {
			log.warn("로컬에서 접속함, 패스");
			chain.doFilter(request, response); // 필터 거쳐서 원래 로직으로 돌아가는 기능
			return;
		}

	}
}
