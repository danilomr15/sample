package br.com.danilomr.postgres.controller;

import br.com.danilomr.postgres.controller.dto.UserDTO;
import br.com.danilomr.postgres.controller.mapper.UserMapper;
import br.com.danilomr.postgres.entity.User;
import br.com.danilomr.postgres.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Validated
@RestController
@RequestMapping(path = "/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    List<UserDTO> findAll() {
        return UserMapper.toDTO(userService.findAll());
    }

    @GetMapping(path = "/{id}")
    UserDTO findById(@PathVariable("id") final Long id) {
        return UserMapper.toDTO(userService.findById(id));
    }

    @GetMapping(path = "/{username}/username")
    UserDTO findByUsername(@PathVariable("username") final String username) {
        return UserMapper.toDTO(userService.findByUsername(username));
    }

    @PostMapping
    UserDTO save(@RequestBody @Valid final UserDTO userDTO) {
        final User user = UserMapper.toEntity(userDTO);
        return UserMapper.toDTO(userService.save(user));
    }

    @PutMapping(path = "/{id}")
    UserDTO update(@PathVariable("id") final Long id,
                   @RequestBody @Valid final UserDTO userDTO) {
        final User user = UserMapper.toEntity(userDTO);
        return UserMapper.toDTO(userService.update(id, user));
    }

    @DeleteMapping(path = "/{id}")
    void delete(@PathVariable("id") final Long id) {
        userService.delete(id);
    }
}
