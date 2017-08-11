package com.kushnir.paperki.webapp.paperki.shop.schedules;

import com.kushnir.paperki.sevice.CategoryBean;

import com.kushnir.paperki.webapp.paperki.shop.mail.Mailer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;

public class UpdaterCSV {

    private static final Logger LOGGER = LogManager.getLogger(UpdaterCSV.class);

    @Autowired
    CategoryBean categoryBean;

    @Autowired
    Mailer mailer;

    public void catalogUpdate() throws IOException {
        LOGGER.debug("===== Started Catalog update ==== >>> ");
    }

    public void productUpdate() {
        LOGGER.debug("===== Started Products update ==== >>> ");
    }

    public void pricesUpdate() {
        LOGGER.debug("===== Started Prices update ==== >>> ");
    }
}
