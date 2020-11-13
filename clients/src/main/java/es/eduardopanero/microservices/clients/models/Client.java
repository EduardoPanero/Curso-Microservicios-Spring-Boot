package es.eduardopanero.microservices.clients.models;

import lombok.Data;
import lombok.NonNull;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.util.UUID;

@Data
@Table("clients")
public class Client  {
	@Id
	private UUID id;

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	@NonNull
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@NonNull
	private String email;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@NonNull
	private String userName;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	@NonNull
	private String password;

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@NonNull
	private String salt;

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	private boolean enabled;

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public Client withId(UUID id){
		this.setId(id);
		return this;
	}

	public Client withName(String name){
		this.setName(name);
		return this;
	}

	public Client withEmail(String email){
		this.setEmail(email);
		return this;
	}

	public Client withUserName(String userName){
		this.setUserName(userName);
		return this;
	}

	public Client withPassword(String password){
		this.setPassword(password);
		return this;
	}

	public Client withSalt(String salt){
		this.setSalt(salt);
		return this;
	}

	public Client withEnabled(boolean enabled){
		this.setEnabled(enabled);
		return this;
	}
}
