package com.safelet.walletserver.controller;

import com.safelet.walletserver.model.Coin;
import com.safelet.walletserver.model.User;
import com.safelet.walletserver.service.UserService;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.annotation.AuthenticationPrincipal;
//import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

/**
 * Recibe las peticiones en JSON desde el cliente y las procesa.
 *
 * @author Anadres Sanchez Martinez
 * @version 1
 */
@RestController
@RequestMapping("/user")
public class UserController {

	private final UserService service;

	public UserController(UserService service) {
		this.service = service;
	}

//	@Operation(summary = "Get the information of the user.")
//	@ApiResponses(value = {
//			@ApiResponse(responseCode = "200", description = "ok.",
//					content = { @Content(mediaType = "application/json",
//							schema = @Schema(implementation = User.class)) }),
//			@ApiResponse(responseCode = "400", description = "Error",
//					content = @Content),
//			@ApiResponse(responseCode = "404", description = "No se ha encontrado",
//					content = @Content) })
//	@GetMapping("/info")
//	public Map<String, Object> getUserInfo(@AuthenticationPrincipal UserDetails userDetails) {
//		Map<String, Object> userInfo = new HashMap<>();
//		userInfo.put("username", userDetails.getUsername());
//		userInfo.put("info", service.getByUsername(userDetails.getUsername()));
//		userInfo.put("details", userDetails.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()));
//
//		return userInfo;
//	}

	@GetMapping("{id}")
	public Optional<User> getById(@PathVariable("id") Long id) {
		return service.getById(id);
	}

	@GetMapping("/{username}/{password}")
	public Optional<User> findByUsernameAndPassword(@PathVariable("username") String username,
													@PathVariable("password") String password){
		return service.findByUsernameAndPassword(username, password);
	}

	@PostMapping
	public User create(@RequestBody User user) {
		return service.create(user);
	}

	@PutMapping
	public User update(@RequestBody User user) {
		return service.update(user);
	}

	@DeleteMapping("/{id}")
	public boolean delete(@PathVariable("id") Long id) {
		return service.delete(id);
	}
}
