package com.przeglad_premier_league.service.scheduled;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.przeglad_premier_league.config.AuthEntryPointJwt;
import com.przeglad_premier_league.model.authentication.User;
import com.przeglad_premier_league.model.authentication.UserRepository;

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@EnableAsync
@RequiredArgsConstructor
public class DeleteOldUsers {

    private final UserRepository userRepository;
    private static final Logger logger = LoggerFactory.getLogger(AuthEntryPointJwt.class);

    @Async
    @Scheduled(cron = "@hourly")
    public void deleteUsers(){
        List<User> users = userRepository.findAll();
        for (User user : users) {
            Date date = new Date();
            if(user.getTimeStamp() != null && user.getConfirmationID() != null)
                if(new Date(user.getTimeStamp().getTime() + 1000 * 60 * 60 * 24).compareTo(date)<0){
                    logger.info("deleted user id: "+user.getId());
                    userRepository.delete(user);
                }
        }
    }
}
