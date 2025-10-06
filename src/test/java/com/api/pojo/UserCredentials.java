package com.api.pojo;

public class UserCredentials {
   private String username;
   private String password;

   public UserCredentials(String username,String password){
       this.username=username;
       this.password=password;
   }
    public String getUsername() {
         return username;
    }
    public String getPassword(){
       return password;
    }
    public String setUsername(String username){
       return this.username=username;
    }
    public String setPassword(String password){
       return this.password=password;
    }

    @Override
    public String toString() {
        return "UserCredentials{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
