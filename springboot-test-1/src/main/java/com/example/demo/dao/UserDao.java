package com.example.demo.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.example.demo.model.Account;

@Mapper
public interface UserDao {

	// 전체 count
	@Select("SELECT COUNT(*) FROM ACCOUNT")
	public int count();

	// 회원가입
	@Insert("INSERT INTO ACCOUNT (id,pw) VALUES (#{id}, #{pw})")
	public Boolean save(@Param("id") String id, @Param("pw") String pw);

	// 로그인
	@Select("SELECT ID, PW, ROLE FROM ACCOUNT WHERE ID = #{id}")
	public Account selectById(@Param("id") String id);
}
