package com.thermondo.notetakingapp.service;

import com.thermondo.notetakingapp.model.UserContext;
import com.thermondo.notetakingapp.model.entities.Session;
import com.thermondo.notetakingapp.model.entities.User;
import com.thermondo.notetakingapp.repository.SessionRepository;
import com.thermondo.notetakingapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.Base64;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    UserRepository userRepository;

    @Autowired
    SessionRepository sessionRepository;

    private static final SecureRandom secureRandom = new SecureRandom(); //threadsafe
    private static final Base64.Encoder base64Encoder = Base64.getUrlEncoder(); //threadsafe
    private static final Long DEFAULT_EXPIRY_TIME = Long.valueOf(1000 * 60 * 5);

    /**
     *
     * @param userRequest
     * @return
     */
    @Override
    public User register(User userRequest) {
        return userRepository.save(userRequest);
    }

    /**
     *
     * @param userRequest
     * @return
     */
    @Override
    public UserContext login(User userRequest) {
        Session session = sessionRepository.save(createSessionFromRequest(userRequest));
        UserContext userContext = new UserContext(session.getSessionToken(), userRequest.getLoginName());
        return userContext;
    }

    /**
     *
     * @param userRequest
     * @return
     */
    private Session createSessionFromRequest(User userRequest) {
        Long expiryTime = System.currentTimeMillis() + DEFAULT_EXPIRY_TIME;// session expiry time set as 5 hours.
        User user = userRepository.findByLoginName(userRequest.getLoginName());
        Session session = new Session();
        session.setUserId(user.getUserId());
        session.setExpiryTime(expiryTime);
        session.setSessionToken(generateSessionToken());
        return session;
    }

    /**
     *
     * @return
     */
    private String generateSessionToken() {
        byte[] randomBytes = new byte[24];
        secureRandom.nextBytes(randomBytes);
        return base64Encoder.encodeToString(randomBytes);
    }
}
