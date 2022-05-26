package br.com.danilomr.postgres.service;

import br.com.danilomr.postgres.entity.User;
import br.com.danilomr.postgres.exception.APIException;
import br.com.danilomr.postgres.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

import static br.com.danilomr.postgres.utils.Constants.*;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User findById(final Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new APIException(String.format(USER_NOT_FOUND_WITH_ID, id), HttpStatus.NOT_FOUND));
    }

    public User findByUsername(final String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new APIException(String.format(USER_NOT_FOUND_WITH_USERNAME, username), HttpStatus.NOT_FOUND));
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User save(final User user) {
        if(userRepository.existsByUsername(user.getUsername())) {
            throw new APIException(String.format(USER_EXISTS_WITH_USERNAME, user.getUsername()), HttpStatus.CONFLICT);
        }
        return userRepository.save(user);
    }

    public User update(final Long id, final User user) {
        final User foundUser = findById(id);
        foundUser.setUsername(user.getUsername());
        foundUser.getAddresses().clear();
        foundUser.getAddresses().addAll(user.getAddresses());
        foundUser.getAddresses().stream().forEach(address -> address.setUser(foundUser));
        return userRepository.save(foundUser);
    }

    public void delete(final Long id) {
        final User user = findById(id);
        userRepository.delete(user);
    }
}
