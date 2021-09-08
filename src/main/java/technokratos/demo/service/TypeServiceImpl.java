package technokratos.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import technokratos.demo.domain.entity.Type;
import technokratos.demo.repository.TypeRepository;

import java.util.List;

@Component
public class TypeServiceImpl implements TypeService {

    @Autowired
    private TypeRepository typeRepository;

    @Override
    public void addType(Type form) {
        Type newType = Type.builder()
                .name(form.getName())
                .build();
        typeRepository.save(newType);
    }

    @Override
    public List<Type> getAllTypes(){
        return typeRepository.findAll();
    }
}
