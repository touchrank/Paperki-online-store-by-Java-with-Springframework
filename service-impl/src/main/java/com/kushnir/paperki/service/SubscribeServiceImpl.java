package com.kushnir.paperki.service;

import com.kushnir.paperki.dao.SubscribeDao;
import com.kushnir.paperki.model.subscribe.SubscribeErrorResponse;
import com.kushnir.paperki.service.exceptions.ServiceException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

public class SubscribeServiceImpl implements SubscribeService {

    private static final Logger LOGGER = LogManager.getLogger(SubscribeServiceImpl.class);

    @Autowired
    SubscribeDao subscribeDao;

    @Override
    @Transactional
    public Object subscribe(String mail, int idEmailList) throws ServiceException {
        LOGGER.debug("subscribe ({}, {})", mail, idEmailList);
        SubscribeErrorResponse subscribeErrorResponse = new SubscribeErrorResponse();
        try {
            Assert.notNull(mail, "email не должен быть пустым");
            Assert.hasText(mail, "email не должен быть пустым");
            Assert.isTrue(validateEmail(mail), "Введен некорректный адрес электронной почты");
        } catch (Exception e) {
            subscribeErrorResponse.setEmail(e.getMessage());
        }

        if(subscribeErrorResponse.isErrors()) {
            LOGGER.error("ОШИБКА ПОДПИСКИ НА РАССЫЛКУ: {}", subscribeErrorResponse);
            return subscribeErrorResponse;
        } else {
            try {
                int id = subscribeDao.subscribe(mail, idEmailList);
                Assert.isTrue(id > 0, "Ошибка подписки на email рассылку");
                LOGGER.debug("Email: {}, успешно подписан на рассылку id: {}", mail, idEmailList);
                return new Integer(id);
            } catch (Exception e) {
                LOGGER.error(e.getMessage());
                throw new ServiceException(e.getMessage());
            }
        }
    }

    private boolean validateEmail(String email) {
        try {
            InternetAddress internetAddress = new InternetAddress(email);
            internetAddress.validate();
            return true;
        } catch (AddressException e) {
            return false;
        }
    }
}