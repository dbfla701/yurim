package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.DAO.UserDao;
import com.example.demo.model.AccessIp;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class AccessIpService {

	@Autowired
	private UserDao dao;
	
	public List<AccessIp> accessIp() {
		log.info("accessIp {}");
		return dao.accessIp();
	}
	
	public boolean delete(long id) {
		log.info("id {}", id);
		return dao.delete(id);
	}
	
	public boolean insertIp(AccessIp ip) {
		log.info("ip {}", ip);
		return dao.insertIp(ip);
	}
}
