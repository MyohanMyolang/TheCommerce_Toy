package com.myolang.thecommerce_toy.infra.querydsl;

import java.util.LinkedList;
import java.util.List;

public abstract class EntityUtil<E, M> {

  protected abstract M entityToModel(E entity);

  protected abstract E modelToEntity(M model);

  protected List<M> entityListToModelList(Iterable<E> entities) {
    List<M> modelList = new LinkedList<>();
    entities.forEach(entity ->
      modelList.add(entityToModel(entity))
    );

    return modelList;
  }

  protected List<M> entityListToModelList(List<E> entities) {
    List<M> modelList = new LinkedList<>();
    entities.forEach(entity ->
      modelList.add(entityToModel(entity))
    );

    return modelList;
  }
}
