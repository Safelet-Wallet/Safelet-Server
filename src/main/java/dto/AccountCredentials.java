package dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * Class used as request body to request login
 */

@Getter
@Setter
@AllArgsConstructor
public class AccountCredentials {
	String username;
	String password;
}
