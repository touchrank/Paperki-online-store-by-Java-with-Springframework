package com.kushnir.paperki.model.subscribe;

public class SubscribeRequest {
    private String name;
    private String email;

    public SubscribeRequest() {
    }

    public SubscribeRequest(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SubscribeRequest that = (SubscribeRequest) o;

        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        return email != null ? email.equals(that.email) : that.email == null;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (email != null ? email.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "SubscribeRequest{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
