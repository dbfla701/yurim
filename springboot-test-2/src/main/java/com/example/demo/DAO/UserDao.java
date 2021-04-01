package com.example.demo.DAO;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.example.demo.model.AccessIp;
import com.example.demo.model.Account;

@Mapper
public interface UserDao {

	// ip 가져오기
	@Select("SELECT IP FROM ACCESS_IP")
	public List<AccessIp> accessIp();

	// count(*) user확인
	@Select("SELECT COUNT(*) FROM ACCOUNT WHERE ROLE='ROLE_ADMIN'")
	public int count();

	// id값이 있는지 없는지 확인
	@Select("SELECT USERNAME FROM ACCOUNT")
	public Account selectId();

	// 회원가입
	@Insert("INSERT INTO ACCOUNT (username,password) VALUES (#{username}, #{password})")
	public boolean signUp(@Param("username") String username, @Param("password") String password);

	// 회원가입
	@Insert("INSERT INTO ACCOUNT (username,password, role) VALUES (#{username}, #{password}, #{role})")
	public boolean signUpAdmin(@Param("username") String username, @Param("password") String password, @Param("role") String role);

	// 로그인
	@Select("SELECT USERNAME, PASSWORD, ROLE FROM ACCOUNT WHERE USERNAME=#{username}")
	public Account selectById(@Param("username") String username);

	// ADMIN계정만 뽑기
	@Select("SELECT USERNAME FROM ACCOUNT WHERE ROLE='ROLE_ADMIN'")
	public List<Account> adminId();

	// IP삭제하기
	@Delete("DELETE FROM ACCESS_IP WHERE ID=#{id}")
	public boolean delete(long id);

	// IP 생성하기
	@Insert("INSERT INTO ACCESS_IP(IP) VALUES(#{ip})")
	public boolean insertIp(AccessIp ip);

}
