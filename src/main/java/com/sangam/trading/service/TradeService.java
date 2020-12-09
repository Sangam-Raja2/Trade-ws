package com.sangam.trading.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.sangam.trading.dao.TradeRepository;
import com.sangam.trading.dao.UsersRepository;
import com.sangam.trading.entity.Trade;
import com.sangam.trading.entity.User;
import com.sangam.trading.exception.UserException;
import com.sangam.trading.model.PriceModel;
import com.sangam.trading.model.TradeModel;
import com.sangam.trading.model.UserModel;

import java.lang.reflect.Type;

/**
 *
 * @author Sangam
 */
@Service
public class TradeService {

	@Autowired
	TradeRepository tradeRepository;
	@Autowired
	UsersRepository userRepositroy;

	/**
	 * User create trade
	 */

	public TradeModel createTrade(TradeModel tradeModel) {
		Trade tradeId = tradeRepository.getByTradeId(tradeModel.getTradeId());
		if (tradeId != null) {
			throw new UserException(HttpStatus.BAD_REQUEST, HttpStatus.BAD_REQUEST.value(),
					" trade id already present, create trades wirh different id", " trade id should be unique");
		}

		ModelMapper modelMapper = new ModelMapper();
		// modelMapper Configuration
		modelMapper.getConfiguration().setFieldMatchingEnabled(true)
				.setFieldAccessLevel(org.modelmapper.config.Configuration.AccessLevel.PRIVATE);
		modelMapper.getConfiguration().setAmbiguityIgnored(true);
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		modelMapper.getConfiguration().setSkipNullEnabled(true);

		User user = new User();
		Trade trade = modelMapper.map(tradeModel, Trade.class);

		if (trade != null) {
			user.setUserName(tradeModel.getUser().getUserName());
			user.setEmail(tradeModel.getUser().getEmail());
			user.setAddress(tradeModel.getUser().getAddress());
			userRepositroy.save(user);
			trade.setUserId(user);
			Trade returnval = tradeRepository.save(trade);
			if (returnval == null) {
				throw new UserException(HttpStatus.FORBIDDEN, HttpStatus.FORBIDDEN.value(), "unable To Create Trade",
						"Kindly Check Your Payload");
			}
		}
		// return values for trademodel like response payload
		TradeModel returnValue = modelMapper.map(trade, TradeModel.class);
		UserModel model = new UserModel();
		model.setUserName(user.getUserName());
		model.setEmail(user.getEmail());
		model.setAddress(user.getAddress());
		model.setUserId(user.getUserId());
		returnValue.setUser(model);
		return returnValue;
	}

	/**
	 * this method to get trade details based on userId
	 */
	public List<User> getTradesByUser(long userId) {
		List<User> users = new ArrayList<>();
		Iterable<User> getUserById = userRepositroy.findByUserId(userId);
		for (User user : getUserById) {
			users.add(user);
		}
		if (users.isEmpty()) {

			throw new UserException(HttpStatus.NOT_FOUND, HttpStatus.NOT_FOUND.value(), "unable get trade Data",
					"record not found for this userid");
		}
		return users;
	}

	/**
	 * this method to get trade all details
	 */
	public List<Trade> findAllTrades() {

		List<Trade> getTrades = tradeRepository.findAll();
		ModelMapper modelMapper = new ModelMapper();
		// Create Conversion Type
		Type listType = new TypeToken<List<Trade>>() {}.getType();
		
		if (getTrades != null) {
			// Convert List of Entity objects to a List of DTOs objects
			List<Trade> returnValue = modelMapper.map(getTrades, listType);
			return returnValue;
		} else {
			throw new UserException(HttpStatus.NOT_FOUND, " check with request");
		}

	}

	/**
	 * this method to delete trade details based on userId
	 */
	public void deleteByTrade(long userId) {
		Boolean isUserValid = null;
		if (isUserValid.TRUE) {
			userRepositroy.deleteById(userId);
			throw new UserException(HttpStatus.ACCEPTED, HttpStatus.ACCEPTED.value(), "trade is deleted",
					"trade deleted by given id");
		}
	}

	/**
	 * this method to get trade details based on stock
	 */
	public List<Trade> getTradeByStrockandTradeType(String symbol, String type, Date startDate, Date endDate) {
		ModelMapper modelMapper = new ModelMapper();
		Boolean isUserValid=false;
		List<Trade> findAll = null;
		// Create Conversion Type
		Type listType = new TypeToken<List<Trade>>() {}.getType();

		if (isUserValid.TRUE) {
			findAll=tradeRepository.findBySymbolandType(symbol, type, startDate, endDate);
			// Convert List of Entity objects to a List of DTOs objects
			List<Trade> returnValue = modelMapper.map(findAll, listType);
			
			if (findAll.isEmpty()){			
			throw new UserException(HttpStatus.NOT_FOUND, HttpStatus.NOT_FOUND.value(), "No trades found",
					"Given date range");
		}
			return returnValue;
	}
		return null;
	}

	/**
	 * this method to get trade details based on price of lowest and highest price
	 */
	public PriceModel getTradeByStrockPrice(String symbol, Date startDate, Date endDate) {
		Double getTradesByLowandMaxPrice=null;
		PriceModel model = new PriceModel();
		Boolean isUserValid = null;
		if (isUserValid.TRUE) {
			getTradesByLowandMaxPrice = tradeRepository.findBySymbolandPrice(symbol, startDate, endDate);
			model.setMax(getTradesByLowandMaxPrice);
			model.setMin(getTradesByLowandMaxPrice);
		} else{
			throw new UserException(HttpStatus.NOT_FOUND, "there are no trades in the given date range");
		}
		
		return model;
	}

	

	}
