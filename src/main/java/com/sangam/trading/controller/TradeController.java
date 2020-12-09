package com.sangam.trading.controller;

import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sangam.trading.dao.UsersRepository;
import com.sangam.trading.entity.Trade;
import com.sangam.trading.entity.User;
import com.sangam.trading.exception.UserException;
import com.sangam.trading.model.PriceModel;
import com.sangam.trading.model.TradeModel;
import com.sangam.trading.service.TradeService;

/**
 *
 * @author Sangam
 */
@RestController
@RequestMapping(value = "/trades")
@CrossOrigin
public class TradeController {

    @Autowired
    TradeService tradeService;

    @Autowired
    UsersRepository userRepositroy;

    /**
     * PostMapping for create trade record
     *
     * @param tradeModel
     * @return
     */
    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}, produces = {
        MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<TradeModel> createTrades(@Valid @RequestBody TradeModel tradeModel) {
        return new ResponseEntity<>(tradeService.createTrade(tradeModel), HttpStatus.CREATED);
    }

    /**
     * GetMapping for get trade details based on user
     *
     * @param userId
     * @return
     */
    @GetMapping(value = "/user/{id}", produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<List<User>> gettradesByUser(@PathVariable("id") long userId) {

        List<User> userResponse = tradeService.getTradesByUser(userId);
        return ResponseEntity.status(HttpStatus.OK).body(userResponse);
    }

    /**
     * GetMapping for get all trade details
     *
     * @return
     */
    @GetMapping(value = "/getAll", produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> getAllRecords() {
        List<Trade> getListTrades = tradeService.findAllTrades();
        return (ResponseEntity<?>) ResponseEntity.status(HttpStatus.OK).body(getListTrades);
    }

    /**
     * DeleteMapping for delete user created trade
     */
    @DeleteMapping("/{id}")
    private ResponseEntity<?> deleteTrade(@PathVariable("id") long id) {
        boolean exists = userRepositroy.existsById(id);
        if (exists) {
            tradeService.deleteByTrade(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {

            return (ResponseEntity<?>) ResponseEntity.status(HttpStatus.NO_CONTENT)
                    .body(new UserException(HttpStatus.NO_CONTENT, "trade records not found"));
        }

    }

    /**
     * GetMapping for get trade information based on stock
     *
     * @param symbol
     * @param type
     * @param startDate
     * @param endDate
     * @return
     */
    @GetMapping(value = "/stocks/{symbol}/trades", produces = {MediaType.APPLICATION_XML_VALUE,
        MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> getStocks(@PathVariable("symbol") String symbol,
            @RequestParam(value = "type", required = true) String type,
            @RequestParam(value = "start", required = true) @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate,
            @RequestParam(value = "end", required = true) @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate) {
        List<Trade> getListTrades = tradeService.getTradeByStrockandTradeType(symbol, type, startDate, endDate);
        return (ResponseEntity<?>) ResponseEntity.status(HttpStatus.OK).body(getListTrades);
    }

    /**
     * GetMapping for get trade information based on price of lowest and highest
     * price
     *
     * @param symbol
     * @param startDate
     * @param endDate
     * @return
     */
    @GetMapping(value = "/stocks/{symbol}/price", produces = {MediaType.APPLICATION_XML_VALUE,
        MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> getStocksByPrice(@PathVariable("symbol") String symbol,
            @RequestParam(value = "start", required = true) @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate,
            @RequestParam(value = "end", required = true) @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate) {
        PriceModel getListTrades = tradeService.getTradeByStrockPrice(symbol, startDate, endDate);
        return (ResponseEntity<?>) ResponseEntity.status(HttpStatus.OK).body(getListTrades);
    }
}
