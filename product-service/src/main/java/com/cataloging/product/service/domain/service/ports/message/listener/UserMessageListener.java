package com.cataloging.product.service.domain.service.ports.message.listener;


import com.cataloging.product.service.domain.service.dto.message.UserModel;

public interface UserMessageListener {
    void userCreated(UserModel userModel);
    void userUpdated(UserModel userModel);
    void userDeleted(UserModel userModel);
}
