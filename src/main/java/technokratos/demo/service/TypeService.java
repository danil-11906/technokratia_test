package technokratos.demo.service;

import technokratos.demo.domain.entity.Type;

import java.util.List;

public interface TypeService {
    void addType(Type form);

    List<Type> getAllTypes();
}
