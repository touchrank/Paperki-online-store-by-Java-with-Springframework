package com.kushnir.paperki.dao;

import com.kushnir.paperki.model.BillingAccount;
import com.kushnir.paperki.model.Enterprise;
import com.kushnir.paperki.model.RegistrateForm;
import com.kushnir.paperki.model.User;

public interface UserDao {
    User getUserByLoginPassword(String userName, String password);
    User getUserByLogin(String login);
    User getUserById(Integer userId);
    Integer addUser(RegistrateForm form);
    Integer addUser(User user);
    Enterprise getEnterpriseByUNP(String unp);
    Integer addEnterprise(Enterprise enterprise);
    Integer addBillingAccount(BillingAccount billingAccount);
}