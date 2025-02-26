package helpers;

import org.aeonbits.owner.Config;

import java.util.List;

@Config.LoadPolicy(Config.LoadType.MERGE)
@Config.Sources({"file:src/main/resources/config.properties"})

public interface ConfigProperties extends Config {
    @Config.Key("base.url")
    String baseUrl();

    @Config.Key("first.product")
    String firstProduct();

    @Config.Key("second.product")
    String secondProduct();

    @Config.Key("list.products")
    List<String>products();

    @Config.Key("start.price")
    String startPrice();

    @Config.Key("end.price")
    String endPrice();

    @Config.Key("time.out")
    Integer timeOut();
}
