package cn.dcr.stock.market.configuration;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "cn.dcr.stock.market")
public class StockMarketEntry {
    public static void main(String[] args) {
            SpringApplication.run(StockMarketEntry.class, args);
        }
}
