package com.kushnir.paperki.webapp.paperki.shop.controllers;

import com.kushnir.paperki.model.MenuItem;
import com.kushnir.paperki.sevice.CategoryBean;
import com.kushnir.paperki.sevice.ComponentBean;
import com.kushnir.paperki.sevice.MenuBean;

import com.kushnir.paperki.webapp.paperki.shop.exceptions.AppException;
import com.kushnir.paperki.webapp.paperki.shop.exceptions.PageNotFound;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/")
public class MainController {

    private static final Logger LOGGER = LogManager.getLogger(MainController.class);
    private static final String MAIN_MENU_NAME = "main";

    @Autowired
    ComponentBean componentBean;

    @Autowired
    CategoryBean categoryBean;

    @Autowired
    MenuBean menuBean;

    @Value("${content.path}")
    String contentPath;

    // главная страница
    @GetMapping()
    public String mainPage(Model model) {
        LOGGER.debug("mainPage() >>>");
        model.addAttribute("mainmenu", menuBean.getAll("root"));
        model.addAttribute("mapcategories", categoryBean.getAll());
        model.addAttribute("templatePathName", contentPath + MAIN_MENU_NAME);
        model.addAttribute("fragmentName", MAIN_MENU_NAME);
        LOGGER.debug("{}", model);
        return "index";
    }

    // страницы главного меню
    @GetMapping("/{pageName}")
    public String mainMenu(@PathVariable String pageName, Model model) throws Exception {
        LOGGER.debug("mainMenu(menuItem = {}) >>>", pageName);
        MenuItem menuItem = menuBean.getRootItem(pageName);
        if (menuItem == null) {
            LOGGER.error("Запрашиваемая страница ({}) не найдена!", pageName);
            throw new PageNotFound();
        }
        if(menuItem.getTranslitName() == null) {
            LOGGER.error("Запрашиваемая страница ({}) не найдена!", pageName);
            throw new PageNotFound();
        }
        pageName = menuItem.getTranslitName();
        model.addAttribute("mainmenu", menuBean.getAll("root"));
        model.addAttribute("mapcategories", categoryBean.getAll());
        model.addAttribute("templatePathName", contentPath + pageName);
        model.addAttribute("fragmentName", pageName);
        LOGGER.debug("{}", model);
        return "index";
    }

}