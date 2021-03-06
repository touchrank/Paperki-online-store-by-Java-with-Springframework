package com.kushnir.paperki.dao;

import com.kushnir.paperki.model.BillingAccount;
import com.kushnir.paperki.model.Enterprise;
import com.kushnir.paperki.model.RegistrateForm;
import com.kushnir.paperki.model.user.Address;
import com.kushnir.paperki.model.user.PasswordRecoveryRequest;
import com.kushnir.paperki.model.user.User;
import com.kushnir.paperki.model.user.UserUpdateRequest;
import org.springframework.dao.DataAccessException;

import java.util.ArrayList;
import java.util.HashMap;

public interface UserDao {
    User getUserByLoginPassword(String userName, String password);
    User getUserByLogin(String login);
    User getUserByEnterpriseUNP(String UNP);
    User getUserById(Integer userId);
    Integer addUser(RegistrateForm form);
    Integer addUser(User user);
    Integer addUserDoNotUseIt(User user);

    Enterprise getEnterpriseByUNP(String unp);
    Enterprise getEnterpriseByUserId(Integer userId);
    Integer addEnterprise(Enterprise enterprise);
    Integer updateEnterprise(Enterprise enterprise);
    Integer addBillingAccount(BillingAccount billingAccount);

    Integer updateUserPassword(String newPassword, Integer userId);
    Integer updateUser (UserUpdateRequest userUpdateRequest, Integer userId);

    Integer addAddress (Address address, Integer userId);
    Integer updateAddress (Address address, Integer userId);
    Integer deleteAddress(Integer idAddress, Integer userId);
    Address getAddressById(Integer addressId);
    HashMap<Integer,ArrayList<Address>> getUserAddresses(Integer userId);

    Integer addPasswordRecoveryRequest(PasswordRecoveryRequest passwordRecoveryRequest);
    PasswordRecoveryRequest getPasswordRecoveryRequestById (Integer id);
    PasswordRecoveryRequest getPasswordRecoveryRequestByToken (String token);
    void expireAllPasswordRecoveryRequestsByUserId(Integer userId);
    void performPasswordRecoveryRequest(Integer id);

    HashMap<String, User> getCustomersFromCSV();
}
