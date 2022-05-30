package br.com.danilomr.mongodb.controller;

import br.com.danilomr.mongodb.controller.dto.UserDTO;
import br.com.danilomr.mongodb.document.User;
import br.com.danilomr.mongodb.mapper.UserMapper;
import br.com.danilomr.mongodb.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    List<UserDTO> findAll() {
        return UserMapper.map(userService.findAll());
    }

    @GetMapping(path = "/{id}")
    UserDTO findById(@PathVariable("id") final String id) {
        return UserMapper.map(userService.findById(id));
    }

    @GetMapping(path = "/{username}/username")
    UserDTO findByUsername(@PathVariable("username") final String username) {
        return UserMapper.map(userService.findByUsername(username));
    }

    @PostMapping
    UserDTO save(@RequestBody @Valid final UserDTO userDTO) {
        final User user = UserMapper.map(userDTO);
        return UserMapper.map(userService.save(user));
    }

    @PutMapping(path = "/{id}")
    UserDTO update(@PathVariable("id") final String id,
                   @RequestBody @Valid final UserDTO userDTO) {
        final User user = UserMapper.map(userDTO);
        return UserMapper.map(userService.update(id, user));
    }

    @DeleteMapping(path = "/{id}")
    void delete(@PathVariable("id") final String id) {
        userService.delete(id);
    }
}
