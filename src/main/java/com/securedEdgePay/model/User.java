package com.securedEdgePay.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.*;

@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
public class User implements UserDetails, Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    @NotEmpty(message = "Please provide a username")
    private String username;

    @Column(nullable = false, columnDefinition = "VARCHAR(255)")
    @NotEmpty(message = "Please provide a password")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    private String email;

    private String firstname;

    private String lastname;

    @Column(nullable = true, unique = true)
    private String confirmationToken;

    private boolean enabled;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "createdAt")
    @JsonFormat(pattern = "YYYY-MM-dd")
    private Date createdAt;

    @UpdateTimestamp
    @JsonFormat(pattern = "YYYY-MM-dd")
    private Date updatedAt;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "rolegroup_id", referencedColumnName = "id")
//    @JsonManagedReference
    private RoleGroup roleGroup;

    @OneToOne
    @JoinColumn(name = "wallet", referencedColumnName = "id")
    private Wallet wallet;

    private String phone;

    public User() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getFullName(){
        return firstname + ' ' + lastname;
    }

    public String getConfirmationToken() {
        return confirmationToken;
    }

    public void setConfirmationToken(String confirmationToken) {
        this.confirmationToken = confirmationToken;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public RoleGroup getRoleGroup() {
        return roleGroup;
    }

    public void setRoleGroup(RoleGroup roleGroup) {
        this.roleGroup = roleGroup;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        if (this.roleGroup == null) {
            return Arrays.asList(new SimpleGrantedAuthority("USER"));
        }

        if(this.roleGroup.getName().equalsIgnoreCase("AGENT")){
            return Arrays.asList(new SimpleGrantedAuthority("ROLE_AGENT"));
        }

        List<Role> roles = this.roleGroup.getRole();

        List<SimpleGrantedAuthority> grantedAuthority = new ArrayList<>();

        for (Role role : roles) {
            grantedAuthority.add(new SimpleGrantedAuthority(role.getRoleName()));
        }

        return grantedAuthority;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public boolean hasRole(String role){
        for(GrantedAuthority authority : getAuthorities()){
            if(authority.getAuthority().equalsIgnoreCase(role))
                return true;
        }

        return false;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Wallet getWallet() {
        return wallet;
    }

    public void setWallet(Wallet wallet) {
        this.wallet = wallet;
    }

    @Override
    public String toString() {
        return "User{" + "id=" + id + ", username='" + username + '\'' + ", password='" + password + '\'' + ", email='"
                + email + '\'' + ", firstname='" + firstname + '\'' + ", lastname='" + lastname + '\''
                + ", confirmationToken='" + confirmationToken + '\'' + ", enabled="
                + enabled + ", createdAt=" + createdAt + ", updatedAt=" + updatedAt + '}';
    }

}
