package com.dao;

import com.po.Record;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Auther: Chang
 * @Date: 2018/10/3
 */
@Mapper
@Repository
public interface SignDao {

	@Insert(value = "INSERT INTO cookies(username, cookie) VALUES(#{username}, #{cookie})")
	void insert(@Param("username") String username,
	            @Param("cookie") String cookie);

	@Select(value = "SELECT cookie FROM cookies WHERE username = #{username}")
	String select(@Param("username") String username);

	@Select(value = "SELECT username FROM cookies")
	List<String> selectAllUse();

	@Select(value = "SELECT username FROM cookies WHERE username = #{username}")
	String checkUseName(@Param("username") String username);

	@Insert(value = "INSERT INTO record(username, title, date, info) VALUES(#{username}, #{title}, #{date}, #{info})")
	void insertRecord(@Param("username") String username,
	                  @Param("title") String title,
	                  @Param("date") String date,
	                  @Param("info") String info);

	@Select(value = "SELECT * FROM record WHERE username = #{username} ORDER BY date DESC LIMIT 225")
	List<Record> selectRecord(@Param("username") String username);
}
