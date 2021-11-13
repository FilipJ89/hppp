package com.pg.hppp.services;

import com.pg.hppp.model.Line;
import com.pg.hppp.model.Supplier;
import com.pg.hppp.model.User;
import com.pg.hppp.repositories.LineRepository;
import com.pg.hppp.repositories.SupplierRepository;
import com.pg.hppp.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;


@AllArgsConstructor
@Service
public class LineService {

    private final LineRepository lineRepository;
    private final SupplierRepository supplierRepository;

    public Set<Line> getAllLinesForAuthUser(User authUser) {
        Set<User> authUsers = new HashSet<>();
        authUsers.add(authUser);
        Set<Supplier> suppliers = supplierRepository.findAllByUsersIn(authUsers);
        return lineRepository.findAllBySupplierIn(suppliers);
    }



}
