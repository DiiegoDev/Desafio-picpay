package com.picpaySimplificado.services.user;

import com.picpaySimplificado.domain.user.User;
import com.picpaySimplificado.domain.user.UserType;
import com.picpaySimplificado.repositories.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.UUID;

@Service
public class UserService {
  @Autowired
  private UserRepository repository;

  public void validateTransaction(User sender, BigDecimal amount) throws Exception {
    if(sender.getUserType() == UserType.MERCHANT) {
      throw new Exception("Logístas não estão autorizados a fazer trasferências");
    }

    if(sender.getBalance().compareTo(amount) < 0) {
      throw new Exception("Saldo insuficiente");
    }
  }

  public User findUserById(UUID id) throws Exception {
    return this.repository.findById(id).orElseThrow(() -> new Exception("Usuário não encontrado"));
  }

  public void saveUser(User user) {
    this.repository.save(user);
  }
}
