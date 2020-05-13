package org.pb.mapper;

public interface AbstractMapper<E, D> {

    E toEntity(D d);

    D toDTO(E e);
}
