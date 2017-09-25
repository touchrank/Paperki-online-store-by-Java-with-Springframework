package com.kushnir.paperki.webapp.paperki.shop.controllers.order;

import com.kushnir.paperki.model.Cart;
import com.kushnir.paperki.model.RestMessage;
import com.kushnir.paperki.model.User;
import com.kushnir.paperki.service.OrderService;
import com.kushnir.paperki.service.mail.Mailer;

import com.kushnir.paperki.webapp.paperki.shop.mail.HtmlMailer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.HashMap;

@CrossOrigin
@RestController
@RequestMapping("/api/order")
public class RESTOrder {

    private static final Logger LOGGER = LogManager.getLogger(RESTOrder.class);

    @Autowired
    private HtmlMailer htmlMailer;

    @Autowired
    OrderService orderService;

    @Autowired
    Mailer mailer;

    @Value("${webapp.host}")
    String host;

    @PostMapping("/submit")
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody RestMessage submitOrder (@RequestBody HashMap<String, String> orderForm,
                                                  HttpSession session) {
        LOGGER.debug("REST ORDER SUBMIT >>>\n{}", orderForm);
        RestMessage restMessage;
        try {
            Cart cart = (Cart) session.getAttribute("cart");
            User user = (User) session.getAttribute("user");

            Object obj = orderService.submitOrder(orderForm, cart, user);

            if(obj instanceof String) {
                restMessage = new RestMessage(HttpStatus.OK, "Заказ успешно создан", obj);
                String orderToken = (String) obj;
                htmlMailer.sendOrderConfirmEmail(orderService.getOrderByToken(orderToken), "a-kush@mail.ru");
                session.setAttribute("cart", new Cart());
            } else {
                restMessage = new RestMessage(HttpStatus.BAD_REQUEST, "Ошибка валидации заказа", obj);
            }

            return restMessage;
        } catch (Exception e) {
            LOGGER.error("ERROR REST ORDER SUBMIT >>>\nERROR MESSAGE{}", e.getMessage());
            restMessage = new RestMessage(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), e);
            mailer.toSupportMail(restMessage.toString(), "ERROR REST ORDER SUBMIT");
            return restMessage;
        }
    }
}