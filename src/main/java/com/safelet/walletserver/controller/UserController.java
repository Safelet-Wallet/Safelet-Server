package com.safelet.walletserver.controller;

import com.safelet.walletserver.model.User;
import com.safelet.walletserver.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Recibe las peticiones en JSON desde el cliente y las procesa.
 *
 * @author Anadres Sanchez Martinez
 * @version 1
 */
@RestController
@RequestMapping("/user")
public class UserController {

	private final UserService userService;

	public UserController(UserService userService) {
		this.userService = userService;
	}

	@Operation(summary = "Get the information of the user.")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "ok.",
					content = { @Content(mediaType = "application/json",
							schema = @Schema(implementation = User.class)) }),
			@ApiResponse(responseCode = "400", description = "Error",
					content = @Content),
			@ApiResponse(responseCode = "404", description = "No se ha encontrado",
					content = @Content) })

	@GetMapping("/info")
	public Map<String, Object> getUserInfo(@AuthenticationPrincipal UserDetails userDetails) {
		Map<String, Object> userInfo = new HashMap<>();
		userInfo.put("username", userDetails.getUsername());
		userInfo.put("info", userService.getUserInfo(userDetails.getUsername()));
		userInfo.put("details", userDetails.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()));

		return userInfo;
	}
}
