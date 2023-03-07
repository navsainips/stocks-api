package com.stocks.api.stocksapi.controller;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.stocks.api.stocksapi.dto.Stock;
import com.stocks.api.stocksapi.repository.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping(path="/api")
public class StocksApiController {

    @Autowired
    private StockRepository stockRepository;

    @RequestMapping(value = "/stock-data/bulk-insert", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<?> uploadFile(@RequestParam("file") MultipartFile file, @RequestHeader("X-Client_Id") String userId) {
        try {
            Reader fileReader = new BufferedReader(new InputStreamReader(file.getInputStream()));
            CsvToBean<String[]> csvToBean = new CsvToBeanBuilder(fileReader)
                    .withType(String[].class)
                    .withIgnoreLeadingWhiteSpace(true)
                    .build();
            CSVReader csvReader = new CSVReaderBuilder(fileReader)
                    .withSkipLines(1)
                    .build();
            List<String[]> allData = csvReader.readAll();

            List<Stock> stocks = new ArrayList<>();
            for (String[] item: allData) {
                Stock tempStock = new Stock(item[0], item[1],item[2], item[3], item[4],item[5], item[6], item[7], item[8], item[9], item[10], item[11],item[12],item[13],item[14],item[15], userId);
                stocks.add(tempStock);
            }

            stockRepository.saveAll(stocks);
            return ResponseEntity.status(HttpStatus.OK).body("File uploaded with success!");
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to insert bulk data from file!");
        }

    } // method uploadFile

    @RequestMapping(
            value = "/stock-data", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    public @ResponseBody Stock addNewStock (@RequestBody Stock stock, @RequestHeader("X-Client_Id") String userId) {
        // @ResponseBody means the returned String is the response, not a view name
        // @RequestParam means it is a parameter from the GET or POST request
        stock.userId = userId;
        Stock savedStock = stockRepository.save(stock);
        return savedStock;
    }

    @RequestMapping(value="/stock-data/{ticker}", method = RequestMethod.GET)
    public @ResponseBody Iterable<Stock> getStockbyTicker(@PathVariable ("ticker") String ticker, @RequestHeader("X-Client_Id") String userId){
        List<Stock> stocks = stockRepository.fetchStockList(ticker, userId);
        return stocks;
    }

    @RequestMapping(value="/stock-data/{ticker}", method = RequestMethod.DELETE)
    public @ResponseBody Integer deleteStockbyTicker(@PathVariable ("ticker") String ticker, @RequestHeader("X-Client_Id") String userId){
        Integer response;
        if(ticker.isBlank()) {
            response = stockRepository.deleteStocksByTickerNull(userId);
        }
        response = stockRepository.deleteStocksByTicker(ticker, userId);
        return response;
    }
}

