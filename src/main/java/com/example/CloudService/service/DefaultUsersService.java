//package com.example.CloudService.Service;
//
//import com.example.CloudService.Model.User;
//
//import javax.xml.bind.ValidationException;
//
//import static java.util.Objects.isNull;
//
//public class DefaultUsersService {
//
//        private void validateUser(User user) throws ValidationException {
//        if (isNull(user)) {
//            throw new ValidationException("Object user is null");
//        }
//        if (isNull(user.getLogin()) || user.getLogin().isEmpty()) {
//            throw new ValidationException("Login is empty");
//        }
//    }
//}
