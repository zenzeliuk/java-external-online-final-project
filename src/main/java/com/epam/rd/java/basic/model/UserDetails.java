package com.epam.rd.java.basic.model;

import java.io.Serializable;
import java.util.Objects;


public class UserDetails extends BaseEntity implements Serializable {

    private String firstName;
    private String lastName;
    private String email;
    private Long phone;
    private int age;

    public UserDetails() {
        //POJO object
    }

    public static Builder builder() {
        return new UserDetails().new Builder();
    }

    public class Builder {
        private Builder() {
        }

        public Builder userId(int id) {
            UserDetails.this.id = id;
            return this;
        }

        public Builder firstName(String firstName) {
            UserDetails.this.firstName = firstName;
            return this;
        }

        public Builder lastName(String lastName) {
            UserDetails.this.lastName = lastName;
            return this;
        }

        public Builder email(String email) {
            UserDetails.this.email = email;
            return this;
        }

        public Builder phone(Long phone) {
            UserDetails.this.phone = phone;
            return this;
        }

        public Builder age(int age) {
            UserDetails.this.age = age;
            return this;
        }

        public UserDetails build() {
            return UserDetails.this;
        }
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getPhone() {
        return phone;
    }

    public void setPhone(Long phone) {
        this.phone = phone;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        UserDetails that = (UserDetails) o;
        return age == that.age
                && Objects.equals(firstName, that.firstName)
                && Objects.equals(lastName, that.lastName)
                && Objects.equals(email, that.email)
                && Objects.equals(phone, that.phone);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), firstName, lastName, email, phone, age);
    }

    @Override
    public String toString() {
        return "UserDetails{" +
                ", userId=" + getId() +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", phone=" + phone +
                ", age=" + age +
                '}';
    }
}
