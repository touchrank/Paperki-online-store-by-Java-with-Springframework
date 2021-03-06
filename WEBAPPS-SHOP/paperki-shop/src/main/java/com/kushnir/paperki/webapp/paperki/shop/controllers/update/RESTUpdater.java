package com.kushnir.paperki.webapp.paperki.shop.controllers.update;

import com.kushnir.paperki.model.RestMessage;
import com.kushnir.paperki.service.BrandService;
import com.kushnir.paperki.service.CatalogBean;
import com.kushnir.paperki.service.ProductBean;
import com.kushnir.paperki.service.UserService;
import com.kushnir.paperki.service.exceptions.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@CrossOrigin
@RestController
@RequestMapping("/api/update")
public class RESTUpdater {

    private static final Logger LOGGER = LogManager.getLogger(RESTUpdater.class);
    private static final String version = "1.0";

    @Value("${webapp.host}")
    String host;

    @Autowired
    CatalogBean catalogBean;

    @Autowired
    ProductBean productBean;

    @Autowired
    BrandService brandService;

    @Autowired
    UserService userService;

    //curl -v [host]:8080/api/update
    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody
    RestMessage version() {
        LOGGER.debug("{} Rest update version() >>>", host);
        RestMessage restMessage = new RestMessage(HttpStatus.OK, version);
        return restMessage;
    }

    //curl -v [host]:8080/api/update/catalog
    @GetMapping("/catalog")
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody RestMessage updateCatalog() throws IOException, ServiceException {
        LOGGER.debug("{} Rest update updateCatalog() >>>", host);

        String report = catalogBean.updateCatalog();

        RestMessage restMessage = new RestMessage(HttpStatus.OK, "update Catalog" ,report);
        return restMessage;
    }

    //curl -v [host]:8080/api/update/products
    @GetMapping("/products")
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody RestMessage updateProducts() throws IOException, ServiceException {
        LOGGER.debug("{} Rest update updateProducts() >>>", host);

        String report = productBean.updateProducts();

        RestMessage restMessage = new RestMessage(HttpStatus.OK, "update Products" ,report);
        return restMessage;
    }

    //curl -v [host]:8080/api/update/products/attributes
    @GetMapping("/products/attributes")
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody RestMessage updateProductAttributes() throws IOException, ServiceException {
        LOGGER.debug("{} Rest update updateProductAttributes() >>>", host);

        String report = productBean.updateProductAttributes();

        RestMessage restMessage = new RestMessage(HttpStatus.OK, "update Product Attributes" ,report);
        return restMessage;
    }

    //curl -v [host]:8080/api/update/products/descriptions
    @GetMapping("/products/descriptions")
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody RestMessage updateProductDescriptions() throws IOException, ServiceException {
        LOGGER.debug("{} Rest update updateProductDescriptions() >>>", host);

        String report = productBean.updateProductDescriptions();

        RestMessage restMessage = new RestMessage(HttpStatus.OK, "update Product Descriptions" ,report);
        return restMessage;
    }


    //curl -v [host]:8080/api/update/products/prices
    @GetMapping("/products/prices")
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody RestMessage updateProductPrices() throws IOException, ServiceException {
        LOGGER.debug("{} Rest update updateProductPrices() >>>", host);

        String report = productBean.updateProductPrices();

        RestMessage restMessage = new RestMessage(HttpStatus.OK, "update Product Prices" ,report);
        return restMessage;
    }

    //curl -v [host]:8080/api/update/products/discounts
    @GetMapping("/products/discounts")
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody RestMessage updateProductDiscounts() throws IOException, ServiceException {
        LOGGER.debug("{} Rest update updateProductDiscounts() >>>", host);

        String report = productBean.updateProductDiscounts();

        RestMessage restMessage = new RestMessage(HttpStatus.OK, "update Product Discounts" ,report);
        return restMessage;
    }

    //curl -v [host]:8080/api/update/brands
    @GetMapping("/brands")
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody RestMessage updateBrands() throws IOException, ServiceException {
        LOGGER.debug("{} Rest update updateBrands() >>>", host);

        String report = brandService.updateBrands();

        RestMessage restMessage = new RestMessage(HttpStatus.OK, "update Brands" ,report);
        return restMessage;
    }

    //curl -v [host]:8080/api/update/stock
    @GetMapping("/stock")
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody RestMessage updateStock() throws IOException, ServiceException {
        LOGGER.debug("{} Rest update updateStock() >>>", host);

        String report = productBean.updateStock();

        RestMessage restMessage = new RestMessage(HttpStatus.OK, "update Stock" ,report);
        return restMessage;
    }

    //curl -v [host]:8080/api/update/customers
    @GetMapping("/customers")
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody RestMessage updateCustomers() throws IOException, ServiceException {
        LOGGER.debug("{} Rest update updateCustomers() >>>", host);

        String report = userService.updateCustomers();

        RestMessage restMessage = new RestMessage(HttpStatus.OK, "update Customers" ,report);
        return restMessage;
    }

}
