/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sangam.trading.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.sangam.trading.entity.Trade;
import com.sangam.trading.entity.User;

/**
 * @author Sangam
 */
@Repository
public interface UsersRepository extends JpaRepository<User, Long> {

	@Query("from User where userName=:userName")
	public User getByUserName(String userName);

	public List<User> findAll();

	public List<User> findByUserId(Long userId);
}
